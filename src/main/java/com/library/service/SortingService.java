package com.library.service;

import com.library.domain.Book;
import com.library.domain.Item;
import com.library.domain.User;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * Generic sorting helper. Pluggable {@link Comparator} instances let callers
 * pick from different sort strategies for users or items.
 *
 * @param <T> the element type being sorted
 */
public class SortingService<T> {

    /**
     * Returns a sorted copy of the given list using the given comparator.
     *
     * @param input      the list to sort
     * @param comparator the ordering to apply
     * @return a new list in sorted order
     */
    public List<T> sortBy(List<T> input, Comparator<T> comparator) {
        List<T> copy = new ArrayList<>(input);
        copy.sort(comparator);
        return copy;
    }

    /** @return comparator that orders items by title, case-insensitive */
    public static Comparator<Item> byTitle() {
        return Comparator.comparing(i -> i.getTitle().toLowerCase());
    }

    /** @return comparator that orders items by status name */
    public static Comparator<Item> byStatus() {
        return Comparator.comparing(i -> i.getStatus().name());
    }

    /** @return comparator that orders books by author, case-insensitive */
    public static Comparator<Book> byAuthor() {
        return Comparator.comparing(b -> b.getAuthor().toLowerCase());
    }

    /** @return comparator that orders users by display name, case-insensitive */
    public static Comparator<User> userByName() {
        return Comparator.comparing(u -> u.getName().toLowerCase());
    }

    /** @return comparator that orders users by their userId */
    public static Comparator<User> userById() {
        return Comparator.comparing(User::getUserId);
    }
}
