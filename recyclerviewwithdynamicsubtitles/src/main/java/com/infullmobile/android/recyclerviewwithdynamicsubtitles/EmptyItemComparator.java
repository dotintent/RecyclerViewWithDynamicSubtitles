package com.infullmobile.android.recyclerviewwithdynamicsubtitles;

import java.util.Comparator;

public class EmptyItemComparator<DataObject> implements Comparator<DataObject> {

    @Override
    public int compare(DataObject lhs, DataObject rhs) {
        return 0;
    }
}
