package com.revature.exceptions;

public class InvalidEntryException extends RuntimeException {
    public InvalidEntryException(String message) {
        super(message);
    }
}
