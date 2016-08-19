package com.infullmobile.android.recyclerviewwithdynamicsubtitles;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertFalse;

public class DataCellItemTest {

    public static final String DATA = "data";
    public static final String DATA2 = "data2";

    @Test
    public void shouldCreateCorrectDataItem() {
        // given
        DataCellItem<String> dataCellItem = new DataCellItem<>(DATA);

        // when
        String data = dataCellItem.getData();
        boolean isHeader = dataCellItem.isHeader();

        // then
        assertThat(data).isEqualTo(DATA);
        assertFalse(isHeader);
    }

    @Test
    public void shouldCompareCorrectly() {
        // given
        DataCellItem<String> dataCellItem  = new DataCellItem<>(DATA);
        DataCellItem<String> dataCellItem2 = new DataCellItem<>(DATA);
        DataCellItem<String> dataCellItem3 = new DataCellItem<>(DATA2);


        // then
        assertThat(dataCellItem).isEqualTo(dataCellItem2);
        assertThat(dataCellItem).isNotEqualTo(dataCellItem3);
        assertThat(dataCellItem2).isNotEqualTo(dataCellItem3);
    }
}