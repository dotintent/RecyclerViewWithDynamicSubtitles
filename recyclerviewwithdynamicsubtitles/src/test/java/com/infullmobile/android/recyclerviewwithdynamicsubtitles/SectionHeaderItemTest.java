package com.infullmobile.android.recyclerviewwithdynamicsubtitles;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertTrue;

public class SectionHeaderItemTest {

    public static final String TITLE = "testTitle";
    public static final String TITLE2 = "testTitle2";

    @Test
    public void shouldCreateCorrectHeaderItem() {
        // given
        SectionHeaderItem headerItem = new SectionHeaderItem(TITLE);

        // when
        String sectionName = headerItem.getSectionName();
        boolean isHeader = headerItem.isHeader();

        // then
        assertThat(sectionName).isEqualTo(TITLE);
        assertTrue(isHeader);
    }

    @Test
    public void shouldCompareCorrectly() {
        // given
        SectionHeaderItem headerItem  = new SectionHeaderItem(TITLE);
        SectionHeaderItem headerItem2 = new SectionHeaderItem(TITLE);
        SectionHeaderItem headerItem3 = new SectionHeaderItem(TITLE2);

        // then
        assertThat(headerItem).isEqualTo(headerItem2);
        assertThat(headerItem).isNotEqualTo(headerItem3);
        assertThat(headerItem2).isNotEqualTo(headerItem3);
    }
}