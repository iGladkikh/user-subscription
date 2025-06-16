package com.igladkikh.subscriptionservice.service;

import com.igladkikh.subscriptionservice.dto.ServiceStatisticDto;
import com.igladkikh.subscriptionservice.dto.SubscriptionRequestDto;
import com.igladkikh.subscriptionservice.dto.SubscriptionResponseDto;

import java.util.List;

public interface SubscriptionService {

    List<ServiceStatisticDto> findTopServices(int limit);

    List<SubscriptionResponseDto> findByUserId(Long userId);

    SubscriptionResponseDto create(Long userId, SubscriptionRequestDto dto);

    void delete(Long userId, Long subscriptionId);
}
