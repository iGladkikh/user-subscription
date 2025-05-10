package com.igladkikh.usersubscription.controller;

import com.igladkikh.usersubscription.annotation.LogMethodArgs;
import com.igladkikh.usersubscription.dto.ServiceStatisticDto;
import com.igladkikh.usersubscription.dto.SubscriptionRequestDto;
import com.igladkikh.usersubscription.dto.SubscriptionResponseDto;
import com.igladkikh.usersubscription.openapi.SubscriptionControllerApi;
import com.igladkikh.usersubscription.service.SubscriptionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class SubscriptionController implements SubscriptionControllerApi {

    private final SubscriptionService subscriptionService;

    @Override
    @GetMapping("/subscriptions/top")
    public List<ServiceStatisticDto> findTopServices(@RequestParam(required = false, defaultValue = "3") int limit) {
        return subscriptionService.findTopServices(limit);
    }

    @Override
    @GetMapping("/users/{userId}/subscriptions")
    public List<SubscriptionResponseDto> findByUserId(@PathVariable Long userId) {
        return subscriptionService.findByUserId(userId);
    }

    @Override
    @LogMethodArgs
    @PostMapping("/users/{userId}/subscriptions")
    @ResponseStatus(HttpStatus.CREATED)
    public SubscriptionResponseDto create(@PathVariable Long userId, @Valid @RequestBody SubscriptionRequestDto dto) {
        return subscriptionService.create(userId, dto);
    }

    @Override
    @DeleteMapping("/users/{userId}/subscriptions/{subscriptionId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long userId, @PathVariable Long subscriptionId) {
        subscriptionService.delete(userId, subscriptionId);
    }
}
