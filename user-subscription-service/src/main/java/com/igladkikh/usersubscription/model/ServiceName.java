package com.igladkikh.usersubscription.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.igladkikh.usersubscription.exception.AppException;
import org.springframework.http.HttpStatus;

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
            throw new AppException(HttpStatus.BAD_REQUEST, msg);
        }
    }
}
