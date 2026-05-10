package com.library.domain;

import com.library.interfaces.Reportable;
import com.library.util.LibraryException;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

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
        if (item == null) {
            throw new LibraryException("Cannot add null item");
        }
        items.add(item);
    }

    /**
     * @param user the user to register
     */
    public void addUser(User user) {
        if (user == null) {
            throw new LibraryException("Cannot add null user");
        }
        users.add(user);
    }

    /**
     * Borrows the given item id on behalf of the given user.
     *
     * @param user   the borrowing user
     * @param itemId the item copy id to borrow
     */
    public void borrowItem(User user, String itemId) {
        Item target = items.stream()
                .filter(i -> i.getId().equals(itemId))
                .findFirst()
                .orElseThrow(() -> new LibraryException("Item not found: " + itemId));
        if (target.getStatus() != ItemStatus.IN_STORE) {
            throw new LibraryException("Item not available: " + itemId);
        }
        if (!user.canBorrow(target)) {
            throw new LibraryException("Borrow rules deny user " + user.getUserId() + " for item " + itemId);
        }
        user.borrow(target);
    }

    /**
     * Returns the given item id on behalf of the given user.
     *
     * @param user   the returning user
     * @param itemId the item copy id to return
     */
    public void returnItem(User user, String itemId) {
        Item target = user.getBorrowedItems().stream()
                .filter(i -> i.getId().equals(itemId))
                .findFirst()
                .orElseThrow(() -> new LibraryException("User does not hold item " + itemId));
        user.returnItem(target);
    }

    /**
     * Performs a case-insensitive title search and collapses copies of the
     * same logical work to a single result.
     *
     * @param title query string
     * @return matching items, deduplicated
     */
    public Set<Item> searchByTitle(String title) {
        String needle = title == null ? "" : title.toLowerCase();
        List<Item> matches = new ArrayList<>();
        for (Item item : items) {
            if (item.getTitle().toLowerCase().contains(needle)) {
                matches.add(item);
            }
        }
        return dedupeByLogicalWork(matches);
    }

    /**
     * Performs a case-insensitive author search across {@link Book} items.
     *
     * @param author query string
     * @return matching items, deduplicated
     */
    public Set<Item> searchByAuthor(String author) {
        String needle = author == null ? "" : author.toLowerCase();
        List<Item> matches = new ArrayList<>();
        for (Item item : items) {
            if (item instanceof Book book && book.getAuthor().toLowerCase().contains(needle)) {
                matches.add(book);
            }
        }
        return dedupeByLogicalWork(matches);
    }

    private static String logicalKey(Item item) {
        if (item instanceof Book b) {
            return "BOOK:" + b.getIsbn();
        }
        if (item instanceof DVD d) {
            return "DVD:" + d.getTitle().toLowerCase() + "|" + d.getDirector().toLowerCase();
        }
        if (item instanceof Magazine m) {
            return "MAG:" + m.getTitle().toLowerCase() + "|" + m.getIssueNumber();
        }
        return "OTHER:" + item.getId();
    }

    private static Set<Item> dedupeByLogicalWork(List<Item> source) {
        Set<String> seen = new LinkedHashSet<>();
        Set<Item> result = new LinkedHashSet<>();
        for (Item item : source) {
            if (seen.add(logicalKey(item))) {
                result.add(item);
            }
        }
        return result;
    }

    /**
     * Recursive search over a list of items by title.
     *
     * @param source the items to search through
     * @param title  query string
     * @return matching items, deduplicated
     */
    public Set<Item> searchRecursive(List<Item> source, String title) {
        String needle = title == null ? "" : title.toLowerCase();
        List<Item> matches = new ArrayList<>();
        recurse(source, 0, needle, matches);
        return dedupeByLogicalWork(matches);
    }

    private static void recurse(List<Item> source, int index, String needle, List<Item> matches) {
        if (index >= source.size()) {
            return;
        }
        Item current = source.get(index);
        if (current.getTitle().toLowerCase().contains(needle)) {
            matches.add(current);
        }
        recurse(source, index + 1, needle, matches);
    }

    /**
     * Stream-based search by title.
     *
     * @param title query string
     * @return matching items, deduplicated
     */
    public Set<Item> searchStream(String title) {
        String needle = title == null ? "" : title.toLowerCase();
        List<Item> matches = items.stream()
                .filter(i -> i.getTitle().toLowerCase().contains(needle))
                .collect(Collectors.toList());
        return dedupeByLogicalWork(matches);
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
