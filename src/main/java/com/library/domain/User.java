package com.library.domain;

import java.util.ArrayList;
import java.util.List;

/**
 * Base class for any library user. Tracks identity and the items currently
 * checked out by this user.
 */
public abstract class User {

    private String userId;
    private String name;
    private List<Item> borrowedItems;

    /**
     * @param userId unique user id
     * @param name   display name
     */
    protected User(String userId, String name) {
        this.userId = userId;
        this.name = name;
        this.borrowedItems = new ArrayList<>();
    }

    /** @return the user id */
    public String getUserId() {
        return userId;
    }

    /** @return the display name */
    public String getName() {
        return name;
    }

    /** @return the list of items this user currently holds */
    public List<Item> getBorrowedItems() {
        return borrowedItems;
    }

    /**
     * Determines whether this user is allowed to borrow the given item right
     * now, given user-type rules and current borrow count.
     *
     * @param item the item being requested
     * @return true if borrowing is permitted
     */
    public abstract boolean canBorrow(Item item);

    /**
     * Records that the given item has been borrowed by this user.
     *
     * @param item the item to add to this user's holdings
     */
    public void borrow(Item item) {
        borrowedItems.add(item);
        item.setStatus(ItemStatus.BORROWED);
    }

    /**
     * Records the return of the given item by this user.
     *
     * @param item the item to remove from this user's holdings
     */
    public void returnItem(Item item) {
        borrowedItems.remove(item);
        item.setStatus(ItemStatus.IN_STORE);
    }
}
