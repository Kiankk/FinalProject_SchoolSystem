package com.library.domain;

import com.library.util.Constants;

/**
 * A student user. Students may only borrow {@link Book} items and are limited
 * by {@link com.library.util.Constants#MAX_BOOKS_STUDENT}.
 */
public class Student extends User {

    /**
     * @param userId unique user id
     * @param name   display name
     */
    public Student(String userId, String name) {
        super(userId, name);
    }

    @Override
    public boolean canBorrow(Item item) {
        if (!(item instanceof Book)) {
            return false;
        }
        return getBorrowedItems().size() < Constants.MAX_BOOKS_STUDENT;
    }
}
