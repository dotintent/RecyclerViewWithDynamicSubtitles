package com.infullmobile.android.recyclerviewwithdynamicsubtitles.sample;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.infullmobile.android.recyclerviewwithdynamicsubtitles.sample.model.Book;

public class BookListItemViewHolder extends RecyclerView.ViewHolder {

    private ImageView bookImage;
    private TextView bookTitle;
    private TextView bookDescription;
    private View eraseButton;

    public BookListItemViewHolder(View itemView) {
        super(itemView);
        bookImage = (ImageView) itemView.findViewById(R.id.bookImage);
        bookTitle = (TextView) itemView.findViewById(R.id.bookTitle);
        bookDescription = (TextView) itemView.findViewById(R.id.bookDescription);
        eraseButton = itemView.findViewById(R.id.eraseButton);
    }

    public void bind(final Book book, final MyDynamicTitlesAdapter.ItemEraseListener listener) {
        bookImage.setImageResource(R.mipmap.ic_launcher);
        bookTitle.setText(book.getTitle());
        bookDescription.setText(book.getDescription());
        eraseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.eraseItem(book);
            }
        });
    }
}
