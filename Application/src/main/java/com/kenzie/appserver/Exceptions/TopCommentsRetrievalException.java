package com.kenzie.appserver.Exceptions;

public class TopCommentsRetrievalException extends RuntimeException {
    public TopCommentsRetrievalException() {
        super("Failed to retrieve top comments.");
    }
}