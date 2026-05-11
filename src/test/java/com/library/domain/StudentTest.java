package com.library.domain;

import com.library.util.Constants;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * Unit tests for {@link Student} borrowing rules.
 */
public class StudentTest {

    @Test
    @DisplayName("Student Asking For Book Below Limit -> Permitted")
    void testCanBorrowBookBelowLimit() {
        Student student = new Student("S100", "Alice");
        Book book = new Book("B1", "X", ItemStatus.IN_STORE, "1234567890123", "A", "G");
        Assertions.assertTrue(student.canBorrow(book));
    }

    @Test
    @DisplayName("Student Asking For DVD -> Denied")
    void testCannotBorrowDvd() {
        Student student = new Student("S100", "Alice");
        DVD dvd = new DVD("D1", "Movie", ItemStatus.IN_STORE, "Dir", 100);
        Assertions.assertFalse(student.canBorrow(dvd));
    }

    @Test
    @DisplayName("Student Asking For Magazine -> Denied")
    void testCannotBorrowMagazine() {
        Student student = new Student("S100", "Alice");
        Magazine magazine = new Magazine("M1", "Mag", ItemStatus.IN_STORE, 1, "Pub");
        Assertions.assertFalse(student.canBorrow(magazine));
    }

    @Test
    @DisplayName("Student At Maximum Book Limit -> Denied")
    void testCannotBorrowAtLimit() {
        Student student = new Student("S100", "Alice");
        for (int i = 0; i < Constants.MAX_BOOKS_STUDENT; i++) {
            student.borrow(new Book("B" + i, "T", ItemStatus.IN_STORE, "1234567890123", "A", "G"));
        }
        Book extra = new Book("B999", "Extra", ItemStatus.IN_STORE, "1234567890123", "A", "G");
        Assertions.assertFalse(student.canBorrow(extra));
    }
}
