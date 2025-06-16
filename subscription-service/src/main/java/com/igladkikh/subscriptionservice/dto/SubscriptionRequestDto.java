package com.igladkikh.subscriptionservice.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.igladkikh.subscriptionservice.model.ServiceName;
import com.igladkikh.subscriptionservice.util.DateUtil;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;

@Data
public class SubscriptionRequestDto {
    @NotNull(message = "Service name is required")
    private ServiceName service;
    @FutureOrPresent
    @NotNull(message = "Expiry date is required")
    @JsonFormat(pattern = DateUtil.DEFAULT_DATE_FORMAT)
    private LocalDate expiryDate;
}