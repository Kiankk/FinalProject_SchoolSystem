package com.library.domain;

import com.library.interfaces.Reportable;

/**
 * An administrative user. Admins do not borrow items but can produce reports
 * over the catalog and trigger CSV backups.
 */
public class Admin extends User implements Reportable {

    /**
     * @param userId unique user id
     * @param name   display name
     */
    public Admin(String userId, String name) {
        super(userId, name);
    }

    @Override
    public boolean canBorrow(Item item) {
        return false;
    }

    @Override
    public String generateReport() {
        return null;
    }

    /**
     * Writes the current users and items out to their CSV files.
     */
    public void backupToCSV() {
    }
}
