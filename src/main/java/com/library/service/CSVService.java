package com.library.service;

import com.library.domain.Item;
import com.library.domain.User;

import java.util.List;

/**
 * Reads and writes the seed CSV files that persist users and items between
 * runs.
 */
public class CSVService {

    /**
     * Loads items from the given CSV path.
     *
     * @param path file system path to the CSV
     * @return parsed item list
     */
    public List<Item> loadItems(String path) {
        return null;
    }

    /**
     * Loads users from the given CSV path.
     *
     * @param path file system path to the CSV
     * @return parsed user list
     */
    public List<User> loadUsers(String path) {
        return null;
    }

    /**
     * Writes the given items out to the given CSV path, overwriting any
     * existing file.
     *
     * @param path  destination file
     * @param items items to serialize
     */
    public void saveItems(String path, List<Item> items) {
    }

    /**
     * Writes the given users out to the given CSV path, overwriting any
     * existing file.
     *
     * @param path  destination file
     * @param users users to serialize
     */
    public void saveUsers(String path, List<User> users) {
    }
}
