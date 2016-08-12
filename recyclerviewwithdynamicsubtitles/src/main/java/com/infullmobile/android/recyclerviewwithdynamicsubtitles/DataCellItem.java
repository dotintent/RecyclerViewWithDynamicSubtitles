package com.infullmobile.android.recyclerviewwithdynamicsubtitles;

public class DataCellItem<T> implements ListItem {

    private T data;

    DataCellItem(T data) {
        this.data = data;
    }

    public T getData() {
        return data;
    }

    @Override
    public boolean isHeader() {
        return false;
    }

    @Override
    public boolean equals(Object other) {
        if (other == null) return false;
        if (!(other instanceof DataCellItem)) return false;
        final DataCellItem otherDataCellItem = (DataCellItem) other;
        return data != null && data.equals(otherDataCellItem.getData());
    }
}
