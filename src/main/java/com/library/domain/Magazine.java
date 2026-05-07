package com.library.domain;

/**
 * A magazine copy in the library catalog.
 */
public class Magazine extends Item {

    private int issueNumber;
    private String publisher;

    /**
     * @param id          unique copy id
     * @param title       magazine title
     * @param status      initial lifecycle status
     * @param issueNumber issue number
     * @param publisher   publisher name
     */
    public Magazine(String id, String title, ItemStatus status, int issueNumber, String publisher) {
        super(id, title, status);
        this.issueNumber = issueNumber;
        this.publisher = publisher;
    }

    /** @return the issue number */
    public int getIssueNumber() {
        return issueNumber;
    }

    /** @return the publisher name */
    public String getPublisher() {
        return publisher;
    }

    @Override
    public String describe() {
        return String.format("Magazine[%s] \"%s\" issue #%d (%s) - %s",
                getId(), getTitle(), issueNumber, publisher, getStatus());
    }
}
