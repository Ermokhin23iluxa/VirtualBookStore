package com.example.virtualBookStore.exceptions;

public class NoSuchReviewException extends RuntimeException {
    public NoSuchReviewException(String message) {
        super(message);
    }
}
