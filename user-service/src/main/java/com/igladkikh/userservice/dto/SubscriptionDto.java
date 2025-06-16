package com.igladkikh.userservice.dto;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;

@Data
public class SubscriptionDto implements Serializable {
    private Long id;
    private Long userId;
    private String service;
    private LocalDate expiryDate;
}