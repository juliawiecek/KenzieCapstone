package com.kenzie.appserver.Exceptions;

public class CommentCreationException extends RuntimeException {
    public CommentCreationException(String message) {
        super("Failed to create new comment: " + message);
    }
}