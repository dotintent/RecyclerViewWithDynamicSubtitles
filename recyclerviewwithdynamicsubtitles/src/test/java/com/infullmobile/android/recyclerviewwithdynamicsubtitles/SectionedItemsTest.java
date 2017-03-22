package com.infullmobile.android.recyclerviewwithdynamicsubtitles;

import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class SectionedItemsTest {

    SectionedItems<String> sectionedItems;
    private static final String FIRST_ITEM = "firstItem";
    private final static String SECOND_ITEM = "secondItem";
    private final static String SECOND_SECOND_ITEM = "secondsecondItem";
    private static final String FIRST_IN_ALPHABETICAL_ORDER_ITEM = "A";
    private static final String SECOND_IN_ALPHABETICAL_ORDER_ITEM = "B";
    private static final String THIRD_IN_ALPHABETICAL_ORDER_ITEM = "C";
    private final static int TWO_HEADER_AND_THREE_ITEMS = 5;

    List<String> testItems = Arrays.asList(FIRST_ITEM, SECOND_ITEM, SECOND_SECOND_ITEM);
    List<String> mixedUpTestItems = Arrays.asList(THIRD_IN_ALPHABETICAL_ORDER_ITEM,
                                                  FIRST_IN_ALPHABETICAL_ORDER_ITEM,
                                                  SECOND_IN_ALPHABETICAL_ORDER_ITEM);

    @Test
    public void shouldCreateCorrectSectionedItemsWithTwoSections() {
        // given
        sectionedItems = new SectionedItems<>(
                new FirstLetterSectionEvaluator(),
                testItems,
                SectionComparators.ASCENDING_COMPARATOR,
                new EmptyItemComparator<String>());

        // when
        List<ListItem> listItems = sectionedItems.getItems();
        Map<String, Section<String>> mapOfSections = sectionedItems.getSections();

        // then
        assertThat(listItems).hasSize(TWO_HEADER_AND_THREE_ITEMS);
        assertTrue  (listItems.get(0).isHeader());
        assertFalse (listItems.get(1).isHeader());
        assertTrue  (listItems.get(2).isHeader());
        assertFalse (listItems.get(3).isHeader());
        assertFalse (listItems.get(4).isHeader());
        assertThat(mapOfSections).hasSize(2);
        assertThat(mapOfSections.get("f").getTitle()).isEqualTo("f");
        assertThat(mapOfSections.get("f").getListItems()).hasSize(2);
        assertThat(mapOfSections.get("s").getTitle()).isEqualTo("s");
        assertThat(mapOfSections.get("s").getListItems()).hasSize(3);
    }

    @Test
    public void shouldCreateCorrectSectionedItemsFromNullItems() {
        // given
        sectionedItems = new SectionedItems<>(
                new FirstLetterSectionEvaluator(),
                null,
                SectionComparators.ASCENDING_COMPARATOR,
                new EmptyItemComparator<String>());

        // when
        List<ListItem> listItems = sectionedItems.getItems();
        Map<String, Section<String>> mapOfSections = sectionedItems.getSections();

        // then
        assertThat(listItems).isEmpty();
        assertThat(mapOfSections).isEmpty();
    }

    @Test
    public void shouldCreateCorrectSectionedItemsFromEmptyList() {
        // given
        sectionedItems = new SectionedItems<>(
                new FirstLetterSectionEvaluator(),
                Collections.<String>emptyList(),
                SectionComparators.ASCENDING_COMPARATOR,
                new EmptyItemComparator<String>());

        // when
        List<ListItem> listItems = sectionedItems.getItems();
        Map<String, Section<String>> mapOfSections = sectionedItems.getSections();

        // then
        assertThat(listItems).isEmpty();
        assertThat(mapOfSections).isEmpty();
    }

    @Test
    public void shouldReturnItemsInAlphabeticalOrder() {
        // given
        sectionedItems = new SectionedItems<>(
                new FirstLetterSectionEvaluator(),
                mixedUpTestItems,
                SectionComparators.ASCENDING_COMPARATOR,
                new AlphabeticalOrderItemComparator());

        // when
        List<ListItem> orderedItems = sectionedItems.getItems();

        // then
        assertThat(((SectionHeaderItem) orderedItems.get(0)).getSectionName()).isEqualTo(FIRST_IN_ALPHABETICAL_ORDER_ITEM);
        assertThat(((SectionHeaderItem) orderedItems.get(2)).getSectionName()).isEqualTo(SECOND_IN_ALPHABETICAL_ORDER_ITEM);
        assertThat(((SectionHeaderItem) orderedItems.get(4)).getSectionName()).isEqualTo(THIRD_IN_ALPHABETICAL_ORDER_ITEM);
    }

    private static class FirstLetterSectionEvaluator implements SectionEvaluator<String> {

        @Override
        public String evaluate(String string) {
            return string != null && string.length() > 0
                   ? string.substring(0, 1)
                   : "/empty";
        }
    }

    private static class AlphabeticalOrderItemComparator implements Comparator<String> {
        @Override
        public int compare(String lhs, String rhs) {
            return lhs.compareTo(rhs);
        }
    }
}