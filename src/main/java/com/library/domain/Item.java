package com.library.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * Base class for any borrowable library item.
 * <p>
 * Each concrete subclass represents a physical copy with a unique {@code id}.
 * Two copies of the same logical work share metadata (title, author, etc.) but
 * have different {@code id}s and independent {@link ItemStatus} values.
 */
@Getter
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public abstract class Item {

    private final String id;
    private final String title;

    @Setter
    private ItemStatus status;

    /**
     * @return a single-line human-readable description of the item, defined by
     * each concrete subclass
     */
    public abstract String describe();
}
