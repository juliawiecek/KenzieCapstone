package com.kenzie.appserver.Exceptions;

public class CommentNotFoundException extends RuntimeException {
    public CommentNotFoundException(String commentId) {
        super("Comment with ID " + commentId + " not found.");
    }
}
