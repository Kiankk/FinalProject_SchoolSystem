package com.library;

import com.library.domain.Admin;
import com.library.domain.Item;
import com.library.domain.Library;
import com.library.domain.User;
import com.library.service.BorrowingService;
import com.library.service.CSVService;
import com.library.service.SortingService;
import com.library.util.Constants;
import com.library.util.LibraryException;

import java.util.List;
import java.util.Scanner;
import java.util.Set;

/**
 * Console entry point for the Library Management System.
 */
public class Main {

    private final Library library = new Library();
    private final CSVService csvService = new CSVService();
    private final BorrowingService borrowingService = new BorrowingService();
    private final SortingService<Item> itemSorter = new SortingService<>();

    /**
     * @param args command-line arguments (unused)
     */
    public static void main(String[] args) {
        new Main().run();
    }

    private void run() {
        bootstrap();
        try (Scanner scanner = new Scanner(System.in)) {
            while (true) {
                printMenu();
                String choice = scanner.nextLine().trim();
                try {
                    switch (choice) {
                        case "1" -> doSearch(scanner);
                        case "2" -> doBorrow(scanner);
                        case "3" -> doReturn(scanner);
                        case "4" -> doReport();
                        case "5" -> doBackup();
                        case "6" -> doListSorted();
                        case "0" -> {
                            System.out.println("Goodbye.");
                            return;
                        }
                        default -> System.out.println("Unknown choice");
                    }
                } catch (LibraryException e) {
                    System.out.println("Error: " + e.getMessage());
                }
            }
        }
    }

    private void bootstrap() {
        List<Item> items = csvService.loadItems(Constants.BOOKS_CSV_PATH);
        List<User> users = csvService.loadUsers(Constants.USERS_CSV_PATH);
        items.forEach(library::addItem);
        users.forEach(library::addUser);
        for (User user : users) {
            if (user instanceof Admin admin) {
                admin.attach(library, csvService);
            }
        }
        System.out.printf("Loaded %d items and %d users%n", items.size(), users.size());
    }

    private void printMenu() {
        System.out.println();
        System.out.println("1) Search by title");
        System.out.println("2) Borrow item");
        System.out.println("3) Return item");
        System.out.println("4) Report");
        System.out.println("5) Backup to CSV");
        System.out.println("6) List items sorted by title");
        System.out.println("0) Quit");
        System.out.print("> ");
    }

    private void doSearch(Scanner scanner) {
        System.out.print("Title contains: ");
        String q = scanner.nextLine();
        Set<Item> results = library.searchStream(q);
        if (results.isEmpty()) {
            System.out.println("(no matches)");
            return;
        }
        results.forEach(i -> System.out.println(i.describe()));
    }

    private void doBorrow(Scanner scanner) {
        User user = promptUser(scanner);
        System.out.print("Item id: ");
        String itemId = scanner.nextLine().trim();
        Item target = library.getItems().stream()
                .filter(i -> i.getId().equals(itemId))
                .findFirst()
                .orElseThrow(() -> new LibraryException("Item not found: " + itemId));
        borrowingService.processBorrow(user, target);
        System.out.println("Borrowed.");
    }

    private void doReturn(Scanner scanner) {
        User user = promptUser(scanner);
        System.out.print("Item id: ");
        String itemId = scanner.nextLine().trim();
        Item target = user.getBorrowedItems().stream()
                .filter(i -> i.getId().equals(itemId))
                .findFirst()
                .orElseThrow(() -> new LibraryException("User does not hold " + itemId));
        borrowingService.processReturn(user, target);
        System.out.println("Returned.");
    }

    private void doReport() {
        System.out.println(library.generateReport());
    }

    private void doBackup() {
        Admin admin = (Admin) library.getUsers().stream()
                .filter(Admin.class::isInstance)
                .findFirst()
                .orElseThrow(() -> new LibraryException("No admin user available"));
        admin.backupToCSV();
        System.out.println("Backup complete.");
    }

    private void doListSorted() {
        List<Item> sorted = itemSorter.sortBy(library.getItems(), SortingService.byTitle());
        sorted.forEach(i -> System.out.println(i.describe()));
    }

    private User promptUser(Scanner scanner) {
        System.out.print("User id: ");
        String userId = scanner.nextLine().trim();
        return library.getUsers().stream()
                .filter(u -> u.getUserId().equals(userId))
                .findFirst()
                .orElseThrow(() -> new LibraryException("User not found: " + userId));
    }
}
