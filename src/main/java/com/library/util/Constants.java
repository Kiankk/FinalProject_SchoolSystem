package com.library.util;

import lombok.experimental.UtilityClass;

/**
 * Holds project-wide constant values.
 */
@UtilityClass
public class Constants {

    /** Maximum number of books a Student is permitted to borrow at once. */
    public final int MAX_BOOKS_STUDENT = 5;

    /** Maximum number of items (any type) a Teacher is permitted to borrow at once. */
    public final int MAX_ITEMS_TEACHER = 10;

    /** File system path to the seed books CSV file. */
    public final String BOOKS_CSV_PATH = "src/main/resources/books.csv";

    /** File system path to the seed users CSV file. */
    public final String USERS_CSV_PATH = "src/main/resources/users.csv";
}
