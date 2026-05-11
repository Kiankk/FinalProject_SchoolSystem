package com.library.service;

import com.library.domain.Item;
import com.library.domain.User;
import com.library.util.LibraryException;

import java.time.LocalDateTime;
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
        if (user == null || item == null) {
            throw new LibraryException("Borrow requires both a user and an item");
        }
        if (!user.canBorrow(item)) {
            throw new LibraryException("User " + user.getUserId() + " cannot borrow " + item.getId());
        }
        borrowQueue.add(LocalDateTime.now() + " BORROW " + user.getUserId() + " -> " + item.getId());
        user.borrow(item);
    }

    /**
     * Processes a return transaction for the given user and item.
     *
     * @param user the returning user
     * @param item the returned item copy
     */
    public void processReturn(User user, Item item) {
        if (user == null || item == null) {
            throw new LibraryException("Return requires both a user and an item");
        }
        if (!user.getBorrowedItems().contains(item)) {
            throw new LibraryException("User " + user.getUserId() + " does not hold " + item.getId());
        }
        user.returnItem(item);
        returnHistory.push(LocalDateTime.now() + " RETURN " + user.getUserId() + " <- " + item.getId());
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
