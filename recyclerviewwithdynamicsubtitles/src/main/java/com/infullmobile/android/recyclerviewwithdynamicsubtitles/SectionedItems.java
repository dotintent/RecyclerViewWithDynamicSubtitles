package com.infullmobile.android.recyclerviewwithdynamicsubtitles;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class SectionedItems<T> {

    private final SectionEvaluator<T> sectionEvaluator;
    private List<Section<T>> listOfOrderedSections = Collections.emptyList();

    SectionedItems(@NonNull SectionEvaluator<T> sectionEvaluator,
                   @Nullable Collection<T> items,
                   @NonNull Comparator<Section> comparator) {
        this.sectionEvaluator = sectionEvaluator;
        setData(items, comparator);
    }

    private void setData(@Nullable Collection<T> items, @NonNull Comparator<Section> comparator) {
        listOfOrderedSections.clear();
        listOfOrderedSections = itemsToSections(items, comparator);
    }

    public List<ListItem> getItems() {
        final List<ListItem> result = new ArrayList<>();
        for (Section<T> section : listOfOrderedSections) {
            result.addAll(section.getListItems());
        }
        return result;
    }

    public Map<String, Section<T>> getSections() {
        final Map<String, Section<T>> mapOfSections = new HashMap<>();
        for (Section<T> section : listOfOrderedSections) {
            mapOfSections.put(section.getTitle(), section);
        }
        return mapOfSections;
    }

    @NonNull
    private List<Section<T>> sortSections(@NonNull Collection<Section<T>> sectionedItems,
                                          @NonNull Comparator<Section> comparator) {
        final List<Section<T>> listOfSections = new LinkedList<>(sectionedItems);
        Collections.sort(listOfSections, comparator);
        return listOfSections;
    }

    @NonNull
    private List<Section<T>> itemsToSections(@Nullable Collection<T> items,
                                             @NonNull Comparator<Section> comparator) {
        return (items != null) ? sortSections(getSectionsOfItems(items), comparator)
                                 : Collections.<Section<T>>emptyList();
    }

    @NonNull
    private Collection<Section<T>> getSectionsOfItems(@Nullable Collection<T> items) {
        final Map<String, Section<T>> mapOfAllAddedSections = new HashMap<>();
        if (items != null) {
            for (T item : items) {
                insertItemIntoSectionMap(mapOfAllAddedSections, item);
            }
        }
        return mapOfAllAddedSections.values();
    }

    private void insertItemIntoSectionMap(@NonNull Map<String, Section<T>> mapOfAllAddedSections,
                                          @NonNull T item) {
        final String sectionTitle = sectionEvaluator.evaluate(item);
        if (mapOfAllAddedSections.containsKey(sectionTitle)) {
            mapOfAllAddedSections.get(sectionTitle).addNewItem(item);
        } else {
            mapOfAllAddedSections.put(sectionTitle, new Section<>(item, sectionTitle));
        }
    }
}
