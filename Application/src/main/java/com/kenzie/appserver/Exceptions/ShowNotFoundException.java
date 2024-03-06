package com.kenzie.appserver.Exceptions;

public class ShowNotFoundException extends RuntimeException {
    public ShowNotFoundException(String showName) {
        super("TV show named '" + showName + "' not found.");
    }
}
