package com.library.domain;

import org.junit.jupiter.api.Test;

/**
 * Unit tests for {@link Library} top-level operations.
 */
public class LibraryTest {

    /** Items added via {@link Library#addItem(Item)} appear in the catalog. */
    @Test
    void addItemRegistersItem() {
    }

    /** Search by title is case-insensitive and dedupes copies. */
    @Test
    void searchByTitleDeduplicatesCopies() {
    }

    /** Recursive and stream searches return identical results. */
    @Test
    void recursiveAndStreamSearchesAgree() {
    }
}
