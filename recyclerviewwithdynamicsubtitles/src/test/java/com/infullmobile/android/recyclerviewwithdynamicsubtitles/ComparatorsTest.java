package com.infullmobile.android.recyclerviewwithdynamicsubtitles;

import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class ComparatorsTest {

    private static final Section<String> lowest = new Section<>("item", "aTitle");
    private static final Section<String> medium = new Section<>("item", "bTitle");
    private static final Section<String> highest = new Section<>("item", "cTitle");

    private List<Section<String>> listOfSections;

    @Before
    public void setUp() throws Exception {
        listOfSections = Arrays.asList(medium, lowest, highest);
    }

    @Test
    public void ascedingComparatorShouldCompareSectionsAscedning() {
        // when
        Collections.sort(listOfSections, Comparators.ASCENDING_COMPARATOR);

        // then
        assertThat(listOfSections.get(0)).isEqualTo(lowest);
        assertThat(listOfSections.get(1)).isEqualTo(medium);
        assertThat(listOfSections.get(2)).isEqualTo(highest);
    }

    @Test
    public void descedingComparatorShouldCompareSectionsDescending() {
        // when
        Collections.sort(listOfSections, Comparators.DESCENDING_COMPARATOR);

        // then
        assertThat(listOfSections.get(0)).isEqualTo(highest);
        assertThat(listOfSections.get(1)).isEqualTo(medium);
        assertThat(listOfSections.get(2)).isEqualTo(lowest);
    }
}