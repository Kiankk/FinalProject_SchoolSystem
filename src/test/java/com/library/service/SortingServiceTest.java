package com.library.service;

import com.library.domain.Book;
import com.library.domain.Item;
import com.library.domain.ItemStatus;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Unit tests for {@link SortingService} and its provided comparators.
 */
public class SortingServiceTest {

    @Test
    @DisplayName("sortBy Items By Title -> Alphabetical Order")
    void testSortItemsByTitle() {
        SortingService<Item> sorter = new SortingService<>();
        List<Item> input = new ArrayList<>();
        input.add(new Book("B1", "Effective Java", ItemStatus.IN_STORE, "1111111111111", "Bloch", "Prog"));
        input.add(new Book("B2", "Clean Code", ItemStatus.IN_STORE, "2222222222222", "Martin", "Prog"));
        input.add(new Book("B3", "Refactoring", ItemStatus.IN_STORE, "3333333333333", "Fowler", "Prog"));

        List<Item> sorted = sorter.sortBy(input, SortingService.byTitle());

        Assertions.assertEquals("Clean Code", sorted.get(0).getTitle());
        Assertions.assertEquals("Effective Java", sorted.get(1).getTitle());
        Assertions.assertEquals("Refactoring", sorted.get(2).getTitle());
    }

    @Test
    @DisplayName("sortBy Returns New List -> Original Unchanged")
    void testSortDoesNotMutateInput() {
        SortingService<Item> sorter = new SortingService<>();
        List<Item> input = new ArrayList<>();
        input.add(new Book("B1", "Z", ItemStatus.IN_STORE, "1111111111111", "A", "G"));
        input.add(new Book("B2", "A", ItemStatus.IN_STORE, "2222222222222", "B", "G"));

        sorter.sortBy(input, SortingService.byTitle());

        Assertions.assertEquals("Z", input.get(0).getTitle());
    }
}
