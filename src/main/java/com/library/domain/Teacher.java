package com.library.domain;

/**
 * A teacher user. Teachers may borrow any {@link Item} type up to
 * {@link com.library.util.Constants#MAX_ITEMS_TEACHER} total.
 */
public class Teacher extends User {

    /**
     * @param userId unique user id
     * @param name   display name
     */
    public Teacher(String userId, String name) {
        super(userId, name);
    }

    @Override
    public boolean canBorrow(Item item) {
        return false;
    }
}
