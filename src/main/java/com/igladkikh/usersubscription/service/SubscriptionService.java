package com.igladkikh.usersubscription.service;

import com.igladkikh.usersubscription.dto.ServiceStatisticDto;
import com.igladkikh.usersubscription.dto.SubscriptionRequestDto;
import com.igladkikh.usersubscription.dto.SubscriptionResponseDto;

import java.util.List;

public interface SubscriptionService {

    List<ServiceStatisticDto> findTopServices(int limit);

    List<SubscriptionResponseDto> findByUserId(Long userId);

    SubscriptionResponseDto create(Long userId, SubscriptionRequestDto dto);

    void delete(Long userId, Long subscriptionId);
}
