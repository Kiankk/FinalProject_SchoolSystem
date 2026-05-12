package com.library.util;

import lombok.experimental.UtilityClass;

/**
 * Static helpers for validating raw input strings before they are accepted by
 * the domain model.
 */
@UtilityClass
public class Validation {

    /**
     * Checks that an ISBN is exactly 13 digits.
     *
     * @param isbn candidate ISBN
     * @return true if the value is a 13-digit numeric string
     */
    public boolean isValidISBN(String isbn) {
        return isbn != null && isbn.matches("\\d{13}");
    }

    /**
     * Checks that a user id is non-blank and well-formed.
     *
     * @param userId candidate user id
     * @return true if acceptable
     */
    public boolean isValidUserId(String userId) {
        return userId != null && userId.matches("[A-Z][A-Z0-9]{2,}");
    }

    /**
     * Checks that a required string is neither null nor blank.
     *
     * @param value the candidate value
     * @return true if it carries non-whitespace content
     */
    public boolean isNonBlank(String value) {
        return value != null && !value.trim().isEmpty();
    }
}
