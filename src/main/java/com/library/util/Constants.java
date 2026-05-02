package com.library.util;

/**
 * Holds project-wide constant values.
 */
public final class Constants {

    private Constants() {}

    /** Maximum number of books a Student is permitted to borrow at once. */
    public static final int MAX_BOOKS_STUDENT = 5;

    /** Maximum number of items (any type) a Teacher is permitted to borrow at once. */
    public static final int MAX_ITEMS_TEACHER = 10;

    /** File system path to the seed books CSV file. */
    public static final String BOOKS_CSV_PATH = "src/main/resources/books.csv";

    /** File system path to the seed users CSV file. */
    public static final String USERS_CSV_PATH = "src/main/resources/users.csv";
}
