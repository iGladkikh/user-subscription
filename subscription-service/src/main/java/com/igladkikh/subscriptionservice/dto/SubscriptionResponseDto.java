package com.igladkikh.subscriptionservice.dto;

import com.igladkikh.subscriptionservice.model.ServiceName;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;

@Data
public class SubscriptionResponseDto implements Serializable {
    private Long id;
    private Long userId;
    private ServiceName service;
    private LocalDate expiryDate;
}