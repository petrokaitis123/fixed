package com.qa.application.exceptions.user;

public class UserNotFoundException  extends RuntimeException {
    public UserNotFoundException(Integer id) {
        super("Could not find the user with id: " + id);
    }
}