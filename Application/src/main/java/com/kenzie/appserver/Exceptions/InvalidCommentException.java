package com.kenzie.appserver.Exceptions;

public class InvalidCommentException extends RuntimeException {
    public InvalidCommentException(String reason) {
        super("Invalid comment: " + reason);
    }
}