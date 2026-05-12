package com.library.domain;

import lombok.Getter;

/**
 * A book copy in the library catalog.
 */
@Getter
public class Book extends Item {

    private final String isbn;
    private final String author;
    private final String genre;

    /**
     * @param id     unique copy id
     * @param title  book title
     * @param status initial lifecycle status
     * @param isbn   13-digit ISBN
     * @param author author name
     * @param genre  genre label
     */
    public Book(String id, String title, ItemStatus status, String isbn, String author, String genre) {
        super(id, title, status);
        this.isbn = isbn;
        this.author = author;
        this.genre = genre;
    }

    @Override
    public String describe() {
        return String.format("Book[%s] \"%s\" by %s (ISBN %s, %s) - %s",
                getId(), getTitle(), author, isbn, genre, getStatus());
    }
}
