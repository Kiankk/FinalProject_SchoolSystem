package com.library.domain;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * Unit tests for {@link Book}.
 */
public class BookTest {

    @Test
    @DisplayName("Book Constructor Values -> Exposed Via Getters")
    void testGettersReturnConstructorValues() {
        Book book = new Book("B100", "Clean Code", ItemStatus.IN_STORE,
                "9780132350884", "Robert C. Martin", "Programming");
        Assertions.assertEquals("B100", book.getId());
        Assertions.assertEquals("Clean Code", book.getTitle());
        Assertions.assertEquals("9780132350884", book.getIsbn());
        Assertions.assertEquals("Robert C. Martin", book.getAuthor());
        Assertions.assertEquals("Programming", book.getGenre());
        Assertions.assertEquals(ItemStatus.IN_STORE, book.getStatus());
    }

    @Test
    @DisplayName("describe On Book -> Contains Title And Author And Isbn")
    void testDescribeIncludesTitleAuthorAndIsbn() {
        Book book = new Book("B100", "Clean Code", ItemStatus.IN_STORE,
                "9780132350884", "Robert C. Martin", "Programming");
        String text = book.describe();
        Assertions.assertTrue(text.contains("Clean Code"));
        Assertions.assertTrue(text.contains("Robert C. Martin"));
        Assertions.assertTrue(text.contains("9780132350884"));
    }

    @Test
    @DisplayName("setStatus On Book -> Updates Status")
    void testSetStatusUpdatesStatus() {
        Book book = new Book("B100", "Clean Code", ItemStatus.IN_STORE,
                "9780132350884", "Robert C. Martin", "Programming");
        book.setStatus(ItemStatus.BORROWED);
        Assertions.assertEquals(ItemStatus.BORROWED, book.getStatus());
    }
}
