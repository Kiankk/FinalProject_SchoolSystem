package com.library.domain;

/**
 * Lifecycle states an {@link Item} copy can be in.
 */
public enum ItemStatus {
    /** The copy is currently checked out. */
    BORROWED,
    /** The copy is on the shelf and available. */
    IN_STORE,
    /** The copy has been reported lost. */
    LOST
}
