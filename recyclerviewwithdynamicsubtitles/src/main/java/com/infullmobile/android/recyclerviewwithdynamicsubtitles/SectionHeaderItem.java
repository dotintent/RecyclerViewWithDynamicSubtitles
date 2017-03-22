package com.infullmobile.android.recyclerviewwithdynamicsubtitles;

class SectionHeaderItem implements ListItem {

    private final String sectionName;

    SectionHeaderItem(String sectionName) {
        this.sectionName = sectionName;
    }

    String getSectionName() {
        return sectionName;
    }

    @Override
    public boolean isHeader() {
        return true;
    }

    @Override
    public boolean equals(Object other) {
        if (other == null) return false;
        if (!(other instanceof SectionHeaderItem)) return false;
        final SectionHeaderItem otherTitleItem = (SectionHeaderItem) other;
        return sectionName != null && sectionName.equals(otherTitleItem.getSectionName());
    }
}
