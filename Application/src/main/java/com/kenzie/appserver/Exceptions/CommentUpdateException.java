package com.kenzie.appserver.Exceptions;

public class CommentUpdateException extends RuntimeException {
    public CommentUpdateException(String commentId, String message) {
        super("Failed to update comment with ID " + commentId + ": " + message);
    }
}