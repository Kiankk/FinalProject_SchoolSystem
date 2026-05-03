package com.library.util;

/**
 * Thrown when an invalid library operation is attempted, such as borrowing
 * past a user's limit or returning an item that was never checked out.
 */
public class LibraryException extends RuntimeException {

    /**
     * @param message human-readable failure description
     */
    public LibraryException(String message) {
        super(message);
    }
}
