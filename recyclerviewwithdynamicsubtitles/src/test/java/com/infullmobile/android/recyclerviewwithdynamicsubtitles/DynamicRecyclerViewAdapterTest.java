package com.infullmobile.android.recyclerviewwithdynamicsubtitles;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.MockitoAnnotations;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.RuntimeEnvironment;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.isA;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;

@RunWith(RobolectricGradleTestRunner.class)
public class DynamicRecyclerViewAdapterTest {

    DynamicRecyclerViewAdapter dynamicRecyclerViewAdapter;

    private static final String FIRST_ITEM = "firstItem";
    private static final String FIRST_ITEM_SECTION_TITLE = new TestSectionEvaluator().evaluate(FIRST_ITEM);
    private final static String SECOND_ITEM = "secondItem";
    private static final String SECOND_ITEM_SECTION_TITLE = new TestSectionEvaluator().evaluate(SECOND_ITEM);
    private List<String> testItems = Arrays.asList(FIRST_ITEM, SECOND_ITEM);

    @Captor ArgumentCaptor<String> titleCaptor;
    @Captor ArgumentCaptor<Collection<DataCellItem>> dataItemsCaptor;
    @Captor ArgumentCaptor<String> dataItemCaptor;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        dynamicRecyclerViewAdapter = spy(new EmptyDynamicRecyclerViewAdapterOfString());
        dynamicRecyclerViewAdapter.setData(testItems, new TestSectionEvaluator());
    }

    @Test
    public void shouldGetCorrectItemCount() {
        assertThat(dynamicRecyclerViewAdapter.getItemCount()).isEqualTo(4);
    }

    @Test
    public void shouldProvideCorrectViewTypeForHeadersAndNonHeader() {
        assertThat(dynamicRecyclerViewAdapter.getItemViewType(0)).isEqualTo(DynamicRecyclerViewAdapter.TITLE_TYPE);
        assertThat(dynamicRecyclerViewAdapter.getItemViewType(1)).isEqualTo(DynamicRecyclerViewAdapter.ITEM_TYPE);
        assertThat(dynamicRecyclerViewAdapter.getItemViewType(2)).isEqualTo(DynamicRecyclerViewAdapter.TITLE_TYPE);
        assertThat(dynamicRecyclerViewAdapter.getItemViewType(3)).isEqualTo(DynamicRecyclerViewAdapter.ITEM_TYPE);
    }

    @Test
    public void shouldDelegateCreateItemViewHolder() {
        // when
        dynamicRecyclerViewAdapter.onCreateViewHolder(new FrameLayout(RuntimeEnvironment.application),
                                                      DynamicRecyclerViewAdapter.ITEM_TYPE);

        // then
        verify(dynamicRecyclerViewAdapter).onCreateViewHolder(any(ViewGroup.class));
    }

    @Test
    public void shouldDelegateCreateTitleViewHolder() {
        // when
        dynamicRecyclerViewAdapter.onCreateViewHolder(new FrameLayout(RuntimeEnvironment.application),
                                                      DynamicRecyclerViewAdapter.TITLE_TYPE);

        // then
        verify(dynamicRecyclerViewAdapter).onCreateTitleViewHolder(any(ViewGroup.class));
    }

    @Test
    public void shouldDelegateBindFirstTitleViewHolder() {
        // given
        RecyclerView.ViewHolder viewHolder = mock(RecyclerView.ViewHolder.class);

        // when
        dynamicRecyclerViewAdapter.onBindViewHolder(viewHolder, 0);

        // then
        verify(dynamicRecyclerViewAdapter)
                .onBindTitleViewHolder(isA(RecyclerView.ViewHolder.class), titleCaptor.capture(), dataItemsCaptor.capture());
        assertThat(titleCaptor.getValue()).isEqualTo(FIRST_ITEM_SECTION_TITLE);
        assertThat(dataItemsCaptor.getValue().toArray()[0]).isEqualTo(FIRST_ITEM);
        assertThat(dataItemsCaptor.getValue().size()).isEqualTo(1);
    }

    @Test
    public void shouldDelegateBindFirstItemViewHolder() {
        // given
        RecyclerView.ViewHolder viewHolder = mock(RecyclerView.ViewHolder.class);

        // when
        dynamicRecyclerViewAdapter.onBindViewHolder(viewHolder, 1);

        // then
        verify(dynamicRecyclerViewAdapter)
                .onBindViewHolder(isA(RecyclerView.ViewHolder.class), dataItemCaptor.capture());
        assertThat(dataItemCaptor.getValue()).isEqualTo(FIRST_ITEM);
    }

    @Test
    public void shouldDelegateBindSecondTitleViewHolder() {
        // given
        RecyclerView.ViewHolder viewHolder = mock(RecyclerView.ViewHolder.class);

        // when
        dynamicRecyclerViewAdapter.onBindViewHolder(viewHolder, 2);

        // then
        verify(dynamicRecyclerViewAdapter)
                .onBindTitleViewHolder(isA(RecyclerView.ViewHolder.class), titleCaptor.capture(), dataItemsCaptor.capture());
        assertThat(titleCaptor.getValue()).isEqualTo(SECOND_ITEM_SECTION_TITLE);
        assertThat(dataItemsCaptor.getValue().toArray()[0]).isEqualTo(SECOND_ITEM);
        assertThat(dataItemsCaptor.getValue().size()).isEqualTo(1);
    }

    @Test
    public void shouldDelegateBindSecondItemViewHolder() {
        // given
        RecyclerView.ViewHolder viewHolder = mock(RecyclerView.ViewHolder.class);

        // when
        dynamicRecyclerViewAdapter.onBindViewHolder(viewHolder, 3);

        // then
        verify(dynamicRecyclerViewAdapter)
                .onBindViewHolder(isA(RecyclerView.ViewHolder.class), dataItemCaptor.capture());
        assertThat(dataItemCaptor.getValue()).isEqualTo(SECOND_ITEM);
    }

    private static final class TestSectionEvaluator implements SectionEvaluator<String> {

        @Override
        public String evaluate(String string) {
            return string != null && string.length() > 0
                   ? string.substring(0, 1)
                   : "/empty";
        }
    }
}