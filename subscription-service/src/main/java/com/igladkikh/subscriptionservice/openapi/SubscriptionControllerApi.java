package com.igladkikh.subscriptionservice.openapi;

import com.igladkikh.subscriptionservice.dto.ServiceStatisticDto;
import com.igladkikh.subscriptionservice.dto.SubscriptionRequestDto;
import com.igladkikh.subscriptionservice.dto.SubscriptionResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.List;

@Tag(name = "Subscription controller")
public interface SubscriptionControllerApi {

    @Operation(summary = "${api.sub.get.top.summary}")
    List<ServiceStatisticDto> findTopServices(int limit);

    @Operation(summary = "${api.sub.get.users.summary}")
    List<SubscriptionResponseDto> findByUserId(Long userId);

    @Operation(summary = "${api.sub.create.summary}")
    SubscriptionResponseDto create(Long userId, SubscriptionRequestDto dto);

    @Operation(summary = "${api.sub.delete.summary}")
    void delete(Long userId, Long subscriptionId);
}
