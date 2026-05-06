package com.library.domain;

import com.library.interfaces.Reportable;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Aggregate root for the library. Owns the collection of items and users and
 * exposes the high-level operations for borrowing, returning, searching and
 * reporting.
 */
public class Library implements Reportable {

    private List<Item> items;
    private List<User> users;

    /**
     * Creates an empty library.
     */
    public Library() {
        this.items = new ArrayList<>();
        this.users = new ArrayList<>();
    }

    /**
     * @param item the item copy to register in the catalog
     */
    public void addItem(Item item) {
    }

    /**
     * @param user the user to register
     */
    public void addUser(User user) {
    }

    /**
     * Borrows the given item id on behalf of the given user.
     *
     * @param user   the borrowing user
     * @param itemId the item copy id to borrow
     */
    public void borrowItem(User user, String itemId) {
    }

    /**
     * Returns the given item id on behalf of the given user.
     *
     * @param user   the returning user
     * @param itemId the item copy id to return
     */
    public void returnItem(User user, String itemId) {
    }

    /**
     * Performs a case-insensitive title search and collapses copies of the
     * same logical work to a single result.
     *
     * @param title query string
     * @return matching items, deduplicated
     */
    public Set<Item> searchByTitle(String title) {
        return null;
    }

    /**
     * Performs a case-insensitive author search across {@link Book} items.
     *
     * @param author query string
     * @return matching items, deduplicated
     */
    public Set<Item> searchByAuthor(String author) {
        return null;
    }

    /**
     * Recursive search over a list of items by title.
     *
     * @param source the items to search through
     * @param title  query string
     * @return matching items, deduplicated
     */
    public Set<Item> searchRecursive(List<Item> source, String title) {
        return null;
    }

    /**
     * Stream-based search by title.
     *
     * @param title query string
     * @return matching items, deduplicated
     */
    public Set<Item> searchStream(String title) {
        return null;
    }

    /** @return the catalog items list */
    public List<Item> getItems() {
        return items;
    }

    /** @return the registered users list */
    public List<User> getUsers() {
        return users;
    }

    @Override
    public String generateReport() {
        return null;
    }
}
