package com.igladkikh.subscriptionservice.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public enum ServiceName {
    YOUTUBE,
    VK,
    NETFLIX,
    YANDEX,
    OKKO,
    AMEDIATEKA;

    @JsonCreator
    public static ServiceName fromString(String str) {
        try {
            return ServiceName.valueOf(str.toUpperCase());
        } catch (IllegalArgumentException e) {
            var msg = "Failed to create Service from string: %s".formatted(str);
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, msg);
        }
    }
}
