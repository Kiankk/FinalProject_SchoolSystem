package com.library.domain;

import com.library.interfaces.Reportable;
import com.library.service.CSVService;
import com.library.util.Constants;
import com.library.util.LibraryException;

/**
 * An administrative user. Admins do not borrow items but can produce reports
 * over the catalog and trigger CSV backups.
 */
public class Admin extends User implements Reportable {

    private Library library;
    private CSVService csvService;

    /**
     * @param userId unique user id
     * @param name   display name
     */
    public Admin(String userId, String name) {
        super(userId, name);
    }

    /**
     * Wires the admin up to the library it administers and the CSV service to
     * use for backups.
     *
     * @param library    the managed library
     * @param csvService the CSV persistence service
     */
    public void attach(Library library, CSVService csvService) {
        this.library = library;
        this.csvService = csvService;
    }

    @Override
    public boolean canBorrow(Item item) {
        return false;
    }

    @Override
    public String generateReport() {
        if (library == null) {
            throw new LibraryException("Admin is not attached to a library");
        }
        return library.generateReport();
    }

    /**
     * Writes the current users and items out to their CSV files.
     */
    public void backupToCSV() {
        if (library == null || csvService == null) {
            throw new LibraryException("Admin is not attached to a library or CSV service");
        }
        csvService.saveItems(Constants.BOOKS_CSV_PATH, library.getItems());
        csvService.saveUsers(Constants.USERS_CSV_PATH, library.getUsers());
    }
}
