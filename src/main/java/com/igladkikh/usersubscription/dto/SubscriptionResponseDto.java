package com.igladkikh.usersubscription.dto;

import com.igladkikh.usersubscription.model.ServiceName;
import lombok.Data;

import java.time.LocalDate;

@Data
public class SubscriptionResponseDto {
    private Long id;
    private Long userId;
    private ServiceName service;
    private LocalDate expiryDate;
}