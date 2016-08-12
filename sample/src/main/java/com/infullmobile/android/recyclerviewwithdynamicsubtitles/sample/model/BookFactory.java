package com.infullmobile.android.recyclerviewwithdynamicsubtitles.sample.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public final class BookFactory {

    private BookFactory() {
        throw new AssertionError("No instances.");
    }

    private static final String BEAUTY_AND_ART = "Beauty and Art";
    private static final String ACTION = "Action";
    private static final String THRILLER = "Thriller";

    public static List<Book> listOfBooks() {
        return new ArrayList<>(Arrays.asList(
                new Book(1, "About Version Control", BEAUTY_AND_ART, "description"),
                new Book(2, "A Short History of Git", ACTION, "description"),
                new Book(3, "title", THRILLER, "description"),
                new Book(4, "Git Basics", BEAUTY_AND_ART, "description"),
                new Book(5, "The Command Line", ACTION, "description"),
                new Book(6, "Installing Git", THRILLER, "description"),
                new Book(7, "First-Time Git Setup", BEAUTY_AND_ART, "description"),
                new Book(8, "Getting Help", ACTION, "description"),
                new Book(9, "Getting a Git Repository", THRILLER, "description"),
                new Book(10, "Viewing the Commit History", BEAUTY_AND_ART, "description"),
                new Book(11, "Undoing Things", ACTION, "description"),
                new Book(12, "Git Aliases", THRILLER, "description")
        ));
    }

    public static Book getActionBook() {
        return new Book(new Random().nextInt(), "RandomActionBook", ACTION, "description");
    }
}
