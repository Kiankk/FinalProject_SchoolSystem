package com.library.service;

import com.library.domain.Book;
import com.library.domain.Item;
import com.library.domain.ItemStatus;
import com.library.domain.Student;
import com.library.domain.User;
import com.library.util.Constants;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

/**
 * Unit tests for {@link CSVService} round-trip behaviour.
 */
public class CSVServiceTest {

    @Test
    @DisplayName("loadItems From Seed CSV -> Returns Eight Items")
    void testLoadItemsParsesAllRows() {
        CSVService service = new CSVService();
        List<Item> items = service.loadItems(Constants.BOOKS_CSV_PATH);
        Assertions.assertEquals(8, items.size());
    }

    @Test
    @DisplayName("loadUsers From Seed CSV -> Returns Five Users")
    void testLoadUsersParsesAllRows() {
        CSVService service = new CSVService();
        List<User> users = service.loadUsers(Constants.USERS_CSV_PATH);
        Assertions.assertEquals(5, users.size());
    }

    @Test
    @DisplayName("saveItems Then loadItems -> Round Trips Input")
    void testSaveThenLoadItemsRoundTrips(@TempDir Path tempDir) {
        CSVService service = new CSVService();
        List<Item> original = new ArrayList<>();
        original.add(new Book("B1", "Clean Code", ItemStatus.IN_STORE,
                "9780132350884", "Robert C. Martin", "Programming"));
        Path file = tempDir.resolve("items.csv");
        service.saveItems(file.toString(), original);
        List<Item> reloaded = service.loadItems(file.toString());
        Assertions.assertEquals(1, reloaded.size());
        Assertions.assertEquals("Clean Code", reloaded.get(0).getTitle());
    }

    @Test
    @DisplayName("saveUsers Then loadUsers -> Round Trips Input")
    void testSaveThenLoadUsersRoundTrips(@TempDir Path tempDir) {
        CSVService service = new CSVService();
        List<User> original = new ArrayList<>();
        original.add(new Student("S100", "Alice Chen"));
        Path file = tempDir.resolve("users.csv");
        service.saveUsers(file.toString(), original);
        List<User> reloaded = service.loadUsers(file.toString());
        Assertions.assertEquals(1, reloaded.size());
        Assertions.assertEquals("Alice Chen", reloaded.get(0).getName());
    }
}
