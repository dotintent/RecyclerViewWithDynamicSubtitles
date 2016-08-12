package com.infullmobile.android.recyclerviewwithdynamicsubtitles.sample.model;

public class Book {
    private final int id;
    private final String title;
    private final String genre;
    private final String description;

    public Book(int id, String title, String genre, String description) {
        this.id = id;
        this.title = title;
        this.genre = genre;
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public String getGenre() {
        return genre;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public boolean equals(Object other) {
        if (other == null) return false;
        if (!(other instanceof Book)) return false;
        Book otherBook = (Book) other;
        return id == otherBook.id
                && title.equals(otherBook.getTitle())
                && genre.equals(otherBook.getGenre())
                && description.equals(otherBook.getDescription());
    }
}
