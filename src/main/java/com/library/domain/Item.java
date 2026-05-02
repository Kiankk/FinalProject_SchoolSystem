package com.library.domain;

/**
 * Base class for any borrowable library item.
 * <p>
 * Each concrete subclass represents a physical copy with a unique {@code id}.
 * Two copies of the same logical work share metadata (title, author, etc.) but
 * have different {@code id}s and independent {@link ItemStatus} values.
 */
public abstract class Item {

    private String id;
    private String title;
    private ItemStatus status;

    /**
     * @param id     unique identifier for this physical copy
     * @param title  display title
     * @param status initial lifecycle state
     */
    protected Item(String id, String title, ItemStatus status) {
        this.id = id;
        this.title = title;
        this.status = status;
    }

    /** @return the unique copy id */
    public String getId() {
        return id;
    }

    /** @return the title of this item */
    public String getTitle() {
        return title;
    }

    /** @return the current lifecycle status */
    public ItemStatus getStatus() {
        return status;
    }

    /**
     * Updates the lifecycle status of this copy.
     *
     * @param status the new status
     */
    public void setStatus(ItemStatus status) {
        this.status = status;
    }

    /**
     * @return a single-line human-readable description of the item, defined by
     * each concrete subclass
     */
    public abstract String describe();
}
