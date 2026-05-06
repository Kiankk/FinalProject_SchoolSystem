package com.library.util;

/**
 * Static helpers for validating raw input strings before they are accepted by
 * the domain model.
 */
public final class Validation {

    private Validation() {}

    /**
     * Checks that an ISBN is exactly 13 digits.
     *
     * @param isbn candidate ISBN
     * @return true if the value is a 13-digit numeric string
     */
    public static boolean isValidISBN(String isbn) {
        return false;
    }

    /**
     * Checks that a user id is non-blank and well-formed.
     *
     * @param userId candidate user id
     * @return true if acceptable
     */
    public static boolean isValidUserId(String userId) {
        return false;
    }

    /**
     * Checks that a required string is neither null nor blank.
     *
     * @param value the candidate value
     * @return true if it carries non-whitespace content
     */
    public static boolean isNonBlank(String value) {
        return false;
    }
}
