package com.library.domain;

/**
 * A book copy in the library catalog.
 */
public class Book extends Item {

    private String isbn;
    private String author;
    private String genre;

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

    /** @return the 13-digit ISBN */
    public String getIsbn() {
        return isbn;
    }

    /** @return the author name */
    public String getAuthor() {
        return author;
    }

    /** @return the genre label */
    public String getGenre() {
        return genre;
    }

    @Override
    public String describe() {
        return String.format("Book[%s] \"%s\" by %s (ISBN %s, %s) - %s",
                getId(), getTitle(), author, isbn, genre, getStatus());
    }
}
