package com.library.domain;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

/**
 * Base class for any library user. Tracks identity and the items currently
 * checked out by this user.
 */
@Getter
public abstract class User {

    private final String userId;
    private final String name;
    private final List<Item> borrowedItems = new ArrayList<>();

    /**
     * @param userId unique user id
     * @param name   display name
     */
    protected User(String userId, String name) {
        this.userId = userId;
        this.name = name;
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
