package com.library.service;

import com.library.domain.Item;
import com.library.domain.User;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Coordinates borrow and return transactions, holding a queue of pending
 * borrow requests and a stack of recent return events.
 */
public class BorrowingService {

    private Queue<String> borrowQueue;
    private Deque<String> returnHistory;

    /**
     * Creates an empty borrowing service.
     */
    public BorrowingService() {
        this.borrowQueue = new LinkedList<>();
        this.returnHistory = new ArrayDeque<>();
    }

    /**
     * Processes a borrow transaction for the given user and item.
     *
     * @param user the borrower
     * @param item the requested item copy
     */
    public void processBorrow(User user, Item item) {
    }

    /**
     * Processes a return transaction for the given user and item.
     *
     * @param user the returning user
     * @param item the returned item copy
     */
    public void processReturn(User user, Item item) {
    }

    /** @return the queue of pending borrow requests */
    public Queue<String> getBorrowQueue() {
        return borrowQueue;
    }

    /** @return the stack of recent return events, most recent on top */
    public Deque<String> getReturnHistory() {
        return returnHistory;
    }
}
