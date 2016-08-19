package com.infullmobile.android.recyclerviewwithdynamicsubtitles;

import org.junit.Test;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertTrue;

public class SectionTest {

    Section<String> section;
    private static final String TEST_TITLE = "sectionTitle";
    private static final String FIRST_ITEM = "firstItem";
    private final static String SECOND_ITEM = "secondItem";
    List<String> testItems = Arrays.asList(FIRST_ITEM, SECOND_ITEM);

    @Test
    public void shouldCreateCorrectSectionFromFirstItemAndTitle() {
        //given
        section = new Section<>(FIRST_ITEM, TEST_TITLE);

        //when
        String title = section.getTitle();
        Collection<String> dataItems = section.getDataItems();
        Collection<ListItem> listItems = section.getListItems();

        //then
        assertThat(title).isEqualTo(TEST_TITLE);
        assertThat(dataItems).hasSize(1);
        assertThat(listItems).hasSize(2);
        assertThat(dataItems.toArray()[0]).isEqualTo(FIRST_ITEM);
        assertTrue(listItems.toArray(new ListItem[listItems.size()])[0].isHeader());
        assertThat((getItem(listItems, 1)).getData()).isEqualTo(FIRST_ITEM);
    }

    @Test
    public void shouldCreateCorrectSectionFromListOfItemsAndTitle() {
        //given
        section = new Section<>(testItems, TEST_TITLE);

        //when
        String title = section.getTitle();
        Collection<String> dataItems = section.getDataItems();
        Collection<ListItem> listItems = section.getListItems();

        //then
        assertThat(title).isEqualTo(TEST_TITLE);
        assertThat(dataItems).hasSize(2);
        assertThat(listItems).hasSize(3);
        assertThat(dataItems.toArray()[0]).isEqualTo(FIRST_ITEM);
        assertThat(dataItems.toArray()[1]).isEqualTo(SECOND_ITEM);
        assertTrue(listItems.toArray(new ListItem[listItems.size()])[0].isHeader());
        assertThat((getItem(listItems, 1)).getData()).isEqualTo(FIRST_ITEM);
        assertThat((getItem(listItems, 2)).getData()).isEqualTo(SECOND_ITEM);
    }

    @Test
    public void shouldAddNewItem() {
        //given
        section = new Section<>(FIRST_ITEM, TEST_TITLE);

        //when
        section.addNewItem(SECOND_ITEM);
        String title = section.getTitle();
        Collection<String> dataItems = section.getDataItems();
        Collection<ListItem> listItems = section.getListItems();

        //then
        assertThat(title).isEqualTo(TEST_TITLE);
        assertThat(dataItems).hasSize(2);
        assertThat(listItems).hasSize(3);
        assertThat(dataItems.toArray()[0]).isEqualTo(FIRST_ITEM);
        assertThat(dataItems.toArray()[1]).isEqualTo(SECOND_ITEM);
        assertTrue(listItems.toArray(new ListItem[listItems.size()])[0].isHeader());
        assertThat((getItem(listItems, 1)).getData()).isEqualTo(FIRST_ITEM);
        assertThat((getItem(listItems, 2)).getData()).isEqualTo(SECOND_ITEM);
    }


    private DataCellItem<String> getItem(Collection<ListItem> listItems, int position) {
        return (DataCellItem<String>)listItems.toArray(new ListItem[listItems.size()])[position];
    }
}