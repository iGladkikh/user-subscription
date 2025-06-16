package com.igladkikh.subscriptionservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class SubscriptionServiceException extends ResponseStatusException {
    public SubscriptionServiceException(HttpStatus status, String message) {
        super(status, message);
    }
}

