package com.igladkikh.userservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class AppException extends ResponseStatusException {
    public AppException(HttpStatus status, String message) {
        super(status, message);
    }
}

