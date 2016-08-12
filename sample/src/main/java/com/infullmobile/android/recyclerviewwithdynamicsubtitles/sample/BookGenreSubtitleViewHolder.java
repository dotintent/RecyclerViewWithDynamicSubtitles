package com.infullmobile.android.recyclerviewwithdynamicsubtitles.sample;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

public class BookGenreSubtitleViewHolder extends RecyclerView.ViewHolder {

    private TextView bookGenre;

    public BookGenreSubtitleViewHolder(View itemView) {
        super(itemView);
        bookGenre = (TextView) itemView.findViewById(R.id.bookGenre);
    }

    public void bind(String sectionName, int size) {
        bookGenre.setText(sectionName + " (" + String.valueOf(size) + ")");
    }
}
