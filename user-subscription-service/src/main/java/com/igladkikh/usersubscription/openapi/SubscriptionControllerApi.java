package com.igladkikh.usersubscription.openapi;

import com.igladkikh.usersubscription.dto.ServiceStatisticDto;
import com.igladkikh.usersubscription.dto.SubscriptionRequestDto;
import com.igladkikh.usersubscription.dto.SubscriptionResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.List;

@Tag(name = "2. Subscription controller")
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
