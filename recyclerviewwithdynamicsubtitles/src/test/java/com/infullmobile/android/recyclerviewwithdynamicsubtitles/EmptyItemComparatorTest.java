package com.infullmobile.android.recyclerviewwithdynamicsubtitles;

import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class EmptyItemComparatorTest {

    private static final String lowest = "aTitle";
    private static final String medium = "bTitle";
    private static final String highest = "cTitle";

    private List<String> listOfItems;

    @Before
    public void setUp() throws Exception {
        listOfItems = Arrays.asList(lowest, medium, highest);
    }

    @Test
    public void shouldReturnItemsInTheSameOrder() {
        // when
        Collections.sort(listOfItems, new EmptyItemComparator<String>());

        // then
        assertThat(listOfItems.get(0)).isEqualTo(lowest);
        assertThat(listOfItems.get(1)).isEqualTo(medium);
        assertThat(listOfItems.get(2)).isEqualTo(highest);
    }
}
