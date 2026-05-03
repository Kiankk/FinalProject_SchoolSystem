package com.library.domain;

/**
 * A DVD copy in the library catalog.
 */
public class DVD extends Item {

    private String director;
    private int durationMinutes;

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

    /** @return the director name */
    public String getDirector() {
        return director;
    }

    /** @return the runtime in minutes */
    public int getDurationMinutes() {
        return durationMinutes;
    }

    @Override
    public String describe() {
        return null;
    }
}
