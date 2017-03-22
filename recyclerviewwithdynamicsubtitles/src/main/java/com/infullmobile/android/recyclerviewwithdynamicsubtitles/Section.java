package com.infullmobile.android.recyclerviewwithdynamicsubtitles;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

class Section<T> {

    private final List<ListItem> items;
    private final List<T> dataItems;
    private final String title;

    Section(@NonNull T firstItem, @NonNull String title) {
        this(Arrays.asList(firstItem), title);
    }

    Section(@NonNull List<T> items, @NonNull String title) {
        this.title = title;
        dataItems = new ArrayList<>(items);
        this.items = createListItems(title, items);
    }

    private List<ListItem> createListItems(String title, List<T> items) {
        final List<ListItem> listItems = new ArrayList<>();
        listItems.add(new SectionHeaderItem(title));
        for (T item : items) {
            listItems.add(new DataCellItem<>(item));
        }
        return listItems;
    }

    void addNewItem(@Nullable T item) {
        if (item != null) {
            dataItems.add(item);
            items.add(new DataCellItem<>(item));
        }
    }

    String getTitle() {
        return title;
    }

    Collection<ListItem> getListItems() {
        return items;
    }

    Collection<T> getDataItems() {
        return dataItems;
    }
}
