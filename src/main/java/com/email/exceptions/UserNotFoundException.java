package com.email.exceptions;

public class UserNotFoundException extends EntryNotFoundException {
    public UserNotFoundException(String message) {
        super(message);
    }
}
