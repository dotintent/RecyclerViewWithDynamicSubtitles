package com.infullmobile.android.recyclerviewwithdynamicsubtitles;

import java.util.Comparator;

public final class SectionComparators {

    private SectionComparators() {
        throw new AssertionError("No instances.");
    }

    public static final Comparator<Section> ASCENDING_COMPARATOR = new Comparator<Section>() {
        @Override
        public int compare(Section lhs, Section rhs) {
            if (lhs == null || lhs.getTitle() == null) return -1;
            if (rhs == null || rhs.getTitle() == null) return 1;
            return lhs.getTitle().compareTo(rhs.getTitle());
        }
    };

    public static final Comparator<Section> DESCENDING_COMPARATOR = new Comparator<Section>() {
        @Override
        public int compare(Section lhs, Section rhs) {
            if (lhs == null || lhs.getTitle() == null) return 1;
            if (rhs == null || rhs.getTitle() == null) return -1;
            return -lhs.getTitle().compareTo(rhs.getTitle());
        }
    };
}
