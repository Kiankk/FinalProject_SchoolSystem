package com.library.service;

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
        return null;
    }
}
