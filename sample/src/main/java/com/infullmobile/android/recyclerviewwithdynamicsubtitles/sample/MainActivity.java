package com.infullmobile.android.recyclerviewwithdynamicsubtitles.sample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.infullmobile.android.recyclerviewwithdynamicsubtitles.SectionEvaluator;
import com.infullmobile.android.recyclerviewwithdynamicsubtitles.sample.model.Book;
import com.infullmobile.android.recyclerviewwithdynamicsubtitles.sample.model.BookFactory;

import java.util.List;

public class MainActivity extends AppCompatActivity implements MyDynamicTitlesAdapter.ItemEraseListener {

    private View addBookButton;
    private RecyclerView itemList;
    private List<Book> books = BookFactory.listOfBooks();
    private MyDynamicTitlesAdapter myDynamicTitlesAdapter = new MyDynamicTitlesAdapter(this);
    private SectionEvaluator<Book> sectionEvaluator = new SectionEvaluator<Book>() {
        @Override
        public String evaluate(Book book) {
            return book.getGenre();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        injectViews();
        setupButton();
        setupList();
    }

    private void injectViews() {
        addBookButton = findViewById(R.id.addButton);
        itemList = (RecyclerView) findViewById(R.id.itemsList);
    }

    private void setupButton() {
        addBookButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                books.add(BookFactory.getActionBook());
                refreshAdapter();
            }
        });
    }

    private void setupList() {
        itemList.setLayoutManager(new LinearLayoutManager(this));
        itemList.setAdapter(myDynamicTitlesAdapter);
        refreshAdapter();
    }

    @Override
    public void eraseItem(Book book) {
        books.remove(book);
        refreshAdapter();
    }

    private void refreshAdapter() {
        myDynamicTitlesAdapter.setData(books, sectionEvaluator);
    }
}
