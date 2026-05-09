package com.library.service;

import com.library.domain.Admin;
import com.library.domain.Book;
import com.library.domain.DVD;
import com.library.domain.Item;
import com.library.domain.ItemStatus;
import com.library.domain.Magazine;
import com.library.domain.Student;
import com.library.domain.Teacher;
import com.library.domain.User;
import com.library.util.LibraryException;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
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
        List<Item> items = new ArrayList<>();
        try (BufferedReader reader = Files.newBufferedReader(Paths.get(path))) {
            String header = reader.readLine();
            if (header == null) {
                return items;
            }
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.isBlank()) {
                    continue;
                }
                String[] parts = line.split(",", -1);
                String type = parts[0].trim();
                String id = parts[1].trim();
                String title = parts[2].trim();
                ItemStatus status = ItemStatus.valueOf(parts[3].trim());
                switch (type) {
                    case "BOOK" -> items.add(new Book(id, title, status, parts[4], parts[5], parts[6]));
                    case "DVD" -> items.add(new DVD(id, title, status, parts[7], Integer.parseInt(parts[8])));
                    case "MAGAZINE" -> items.add(new Magazine(id, title, status, Integer.parseInt(parts[9]), parts[10]));
                    default -> throw new LibraryException("Unknown item type: " + type);
                }
            }
        } catch (IOException e) {
            throw new LibraryException("Failed to load items: " + e.getMessage());
        }
        return items;
    }

    /**
     * Loads users from the given CSV path.
     *
     * @param path file system path to the CSV
     * @return parsed user list
     */
    public List<User> loadUsers(String path) {
        List<User> users = new ArrayList<>();
        try (BufferedReader reader = Files.newBufferedReader(Paths.get(path))) {
            String header = reader.readLine();
            if (header == null) {
                return users;
            }
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.isBlank()) {
                    continue;
                }
                String[] parts = line.split(",", -1);
                String type = parts[0].trim();
                String userId = parts[1].trim();
                String name = parts[2].trim();
                switch (type) {
                    case "STUDENT" -> users.add(new Student(userId, name));
                    case "TEACHER" -> users.add(new Teacher(userId, name));
                    case "ADMIN" -> users.add(new Admin(userId, name));
                    default -> throw new LibraryException("Unknown user type: " + type);
                }
            }
        } catch (IOException e) {
            throw new LibraryException("Failed to load users: " + e.getMessage());
        }
        return users;
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
