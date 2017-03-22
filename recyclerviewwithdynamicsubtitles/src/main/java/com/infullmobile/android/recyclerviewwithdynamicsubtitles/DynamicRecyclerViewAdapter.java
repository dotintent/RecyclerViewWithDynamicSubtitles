package com.infullmobile.android.recyclerviewwithdynamicsubtitles;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

public abstract class DynamicRecyclerViewAdapter
        <ViewHolder extends RecyclerView.ViewHolder, TitleViewHolder extends RecyclerView.ViewHolder, DataObject>
        extends RecyclerView.Adapter {

    static final int TITLE_TYPE = 0;
    static final int ITEM_TYPE = 1;

    private List<ListItem> sectionedItemList = Collections.emptyList();
    private Map<String, Section<DataObject>> mapOfSections = Collections.emptyMap();

    public abstract ViewHolder onCreateViewHolder(@NonNull ViewGroup parent);

    public abstract TitleViewHolder onCreateTitleViewHolder(@NonNull ViewGroup parent);

    public abstract void onBindViewHolder(@NonNull ViewHolder holder, @NonNull DataObject item, int position);

    public abstract void onBindTitleViewHolder(@NonNull TitleViewHolder holder,
                                               @NonNull String sectionName,
                                               @NonNull Collection<DataObject> dataItems,
                                               int position);

    @Override
    public final RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return TITLE_TYPE == viewType ? onCreateTitleViewHolder(parent) : onCreateViewHolder(parent);
    }

    @Override
    public final void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        final ListItem item = sectionedItemList.get(position);
        if (item.isHeader()) {
            final String sectionName = ((SectionHeaderItem) item).getSectionName();
            onBindTitleViewHolder((TitleViewHolder) holder,
                                  sectionName,
                                  mapOfSections.get(sectionName).getDataItems(),
                                  position);
        } else {
            onBindViewHolder((ViewHolder) holder, ((DataCellItem<DataObject>) item).getData(), position);
        }
    }

    @Override
    public final int getItemViewType(int position) {
        return sectionedItemList.get(position).isHeader() ? TITLE_TYPE : ITEM_TYPE;
    }

    @Override
    public final int getItemCount() {
        return sectionedItemList.size();
    }

    public final void setData(@Nullable Collection<DataObject> items,
                              @NonNull SectionEvaluator<DataObject> sectionEvaluator) {
        setData(items, sectionEvaluator, Comparators.ASCENDING_COMPARATOR);
    }

    public final void setData(@Nullable Collection<DataObject> items,
                              @NonNull SectionEvaluator<DataObject> sectionEvaluator,
                              @NonNull Comparator<Section> comparator) {
        final SectionedItems<DataObject> sectionedItems = new SectionedItems<>(sectionEvaluator, items, comparator);
        mapOfSections = sectionedItems.getSections();
        insertNewItems(sectionedItems);
    }

    private void insertNewItems(SectionedItems<DataObject> sectionedItems) {
        final List<ListItem> newListItems = sectionedItems.getItems();
        if (sectionedItemList.isEmpty()) {
            sectionedItemList = newListItems;
            notifyDataSetChanged();
        } else {
            removeUnnecessaryItems(newListItems);
            addMissingItems(newListItems);
            updateTitles();
        }
    }

    private void removeUnnecessaryItems(List<? extends ListItem> newItems) {
        final List<ListItem> unnecessaryItems = new ArrayList<>(sectionedItemList);
        unnecessaryItems.removeAll(newItems);
        for (ListItem listItem : unnecessaryItems) {
            int position = sectionedItemList.indexOf(listItem);
            sectionedItemList.remove(position);
            notifyItemRemoved(position);
        }
    }

    private void addMissingItems(List<? extends ListItem> newItems) {
        final List<ListItem> missingItems = new ArrayList<>(newItems);
        missingItems.removeAll(sectionedItemList);
        for (ListItem listItem : missingItems) {
            int position = newItems.indexOf(listItem);
            sectionedItemList.add(position, listItem);
            notifyItemInserted(position);
        }
    }

    private void updateTitles() {
        int index = 0;
        for (final ListItem listItem : sectionedItemList) {
            if (listItem.isHeader()) {
                notifyItemChanged(index);
            }
            ++index;
        }
    }
}
