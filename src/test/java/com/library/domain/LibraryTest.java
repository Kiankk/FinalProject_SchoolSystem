package com.library.domain;

import com.library.util.LibraryException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Set;

/**
 * Unit tests for {@link Library} top-level operations.
 */
public class LibraryTest {

    private Library buildLibraryWithTwoCleanCodeCopies() {
        Library library = new Library();
        library.addItem(new Book("B1", "Clean Code", ItemStatus.IN_STORE,
                "9780132350884", "Robert C. Martin", "Programming"));
        library.addItem(new Book("B2", "Clean Code", ItemStatus.IN_STORE,
                "9780132350884", "Robert C. Martin", "Programming"));
        library.addItem(new Book("B3", "Effective Java", ItemStatus.IN_STORE,
                "9780134685991", "Joshua Bloch", "Programming"));
        return library;
    }

    @Test
    @DisplayName("addItem On Library -> Item Appears In Catalog")
    void testAddItemRegistersItem() {
        Library library = new Library();
        Book book = new Book("B1", "X", ItemStatus.IN_STORE, "1234567890123", "A", "G");
        library.addItem(book);
        Assertions.assertEquals(1, library.getItems().size());
        Assertions.assertEquals(book, library.getItems().get(0));
    }

    @Test
    @DisplayName("searchByTitle With Two Copies -> Dedupes Result")
    void testSearchByTitleDeduplicatesCopies() {
        Library library = buildLibraryWithTwoCleanCodeCopies();
        Set<Item> results = library.searchByTitle("clean code");
        Assertions.assertEquals(1, results.size());
    }

    @Test
    @DisplayName("searchByTitle With Mixed Case -> Case Insensitive Match")
    void testSearchByTitleIsCaseInsensitive() {
        Library library = buildLibraryWithTwoCleanCodeCopies();
        Set<Item> results = library.searchByTitle("CLEAN");
        Assertions.assertEquals(1, results.size());
    }

    @Test
    @DisplayName("searchRecursive And searchStream -> Identical Results")
    void testRecursiveAndStreamSearchesAgree() {
        Library library = buildLibraryWithTwoCleanCodeCopies();
        Set<Item> recursive = library.searchRecursive(library.getItems(), "clean");
        Set<Item> stream = library.searchStream("clean");
        Assertions.assertEquals(recursive.size(), stream.size());
    }

    @Test
    @DisplayName("borrowItem For Student Within Limit -> Status Becomes Borrowed")
    void testBorrowItemTransitionsStatus() {
        Library library = new Library();
        Book book = new Book("B1", "Clean Code", ItemStatus.IN_STORE,
                "9780132350884", "Robert C. Martin", "Programming");
        library.addItem(book);
        Student student = new Student("S1", "Alice");
        library.addUser(student);
        library.borrowItem(student, "B1");
        Assertions.assertEquals(ItemStatus.BORROWED, book.getStatus());
        Assertions.assertTrue(student.getBorrowedItems().contains(book));
    }

    @Test
    @DisplayName("borrowItem When All Copies Out -> Throws LibraryException")
    void testBorrowItemAlreadyOutThrows() {
        Library library = new Library();
        Book book = new Book("B1", "Clean Code", ItemStatus.BORROWED,
                "9780132350884", "Robert C. Martin", "Programming");
        library.addItem(book);
        Student student = new Student("S1", "Alice");
        library.addUser(student);
        Assertions.assertThrows(LibraryException.class, () -> library.borrowItem(student, "B1"));
    }
}
