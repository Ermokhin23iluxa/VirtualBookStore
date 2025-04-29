package com.example.virtualBookStore.exceptions;

public class NoSuchBookException extends RuntimeException {
    public NoSuchBookException(String message) {
        super(message);
    }
}
