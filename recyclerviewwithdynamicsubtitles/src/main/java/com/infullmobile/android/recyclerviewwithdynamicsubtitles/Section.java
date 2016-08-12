package com.infullmobile.android.recyclerviewwithdynamicsubtitles;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class Section<T> {

    private final List<ListItem> items;
    private final List<T> dataItems;
    private final String title;

    public Section(@NonNull T firstItem, @NonNull String title) {
        this(Arrays.asList(firstItem), title);
    }

    public Section(@NonNull List<T> items, @NonNull String title) {
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

    public void addNewItem(@Nullable T item) {
        if (item != null) {
            dataItems.add(item);
            items.add(new DataCellItem<>(item));
        }
    }

    public String getTitle() {
        return title;
    }

    public Collection<ListItem> getListItems() {
        return items;
    }

    public Collection<T> getDataItems() {
        return dataItems;
    }
}
