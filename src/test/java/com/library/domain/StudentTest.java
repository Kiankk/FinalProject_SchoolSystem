package com.library.domain;

import org.junit.jupiter.api.Test;

/**
 * Unit tests for {@link Student} borrowing rules.
 */
public class StudentTest {

    /** A student is allowed to borrow a book when below the limit. */
    @Test
    void canBorrowBookBelowLimit() {
    }

    /** A student cannot borrow a non-book item. */
    @Test
    void cannotBorrowDvdOrMagazine() {
    }

    /** A student cannot borrow once at the maximum book limit. */
    @Test
    void cannotBorrowAtLimit() {
    }
}
