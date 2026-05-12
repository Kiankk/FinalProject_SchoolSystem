package com.library.domain;

import lombok.Getter;

/**
 * A DVD copy in the library catalog.
 */
@Getter
public class DVD extends Item {

    private final String director;
    private final int durationMinutes;

    /**
     * @param id              unique copy id
     * @param title           DVD title
     * @param status          initial lifecycle status
     * @param director        name of the director
     * @param durationMinutes runtime in minutes
     */
    public DVD(String id, String title, ItemStatus status, String director, int durationMinutes) {
        super(id, title, status);
        this.director = director;
        this.durationMinutes = durationMinutes;
    }

    @Override
    public String describe() {
        return String.format("DVD[%s] \"%s\" dir. %s, %d min - %s",
                getId(), getTitle(), director, durationMinutes, getStatus());
    }
}
