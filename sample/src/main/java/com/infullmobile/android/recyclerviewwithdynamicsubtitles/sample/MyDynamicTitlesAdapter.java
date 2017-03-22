package com.infullmobile.android.recyclerviewwithdynamicsubtitles.sample;

import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.infullmobile.android.recyclerviewwithdynamicsubtitles.DynamicRecyclerViewAdapter;
import com.infullmobile.android.recyclerviewwithdynamicsubtitles.sample.model.Book;

import java.util.Collection;

public class MyDynamicTitlesAdapter
        extends DynamicRecyclerViewAdapter <BookListItemViewHolder, BookGenreSubtitleViewHolder, Book> {

    private final ItemEraseListener listener;

    public MyDynamicTitlesAdapter(ItemEraseListener listener) {
        this.listener = listener;
    }

    @Override
    public BookListItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent) {
        return new BookListItemViewHolder(
                LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false)
        );
    }

    @Override
    public BookGenreSubtitleViewHolder onCreateTitleViewHolder(@NonNull ViewGroup parent) {
        return new BookGenreSubtitleViewHolder(
                LayoutInflater.from(parent.getContext()).inflate(R.layout.list_subtitle, parent, false)
        );
    }

    @Override
    public void onBindViewHolder(@NonNull BookListItemViewHolder holder,
                                 @NonNull Book book,
                                 int position) {
        holder.bind(book, listener);
    }

    @Override
    public void onBindTitleViewHolder(@NonNull BookGenreSubtitleViewHolder holder,
                                      @NonNull String sectionName,
                                      @NonNull Collection<Book> dataItems,
                                      int position) {
        holder.bind(sectionName, dataItems.size());
    }

    interface ItemEraseListener {
        void eraseItem(Book book);
    }
}
