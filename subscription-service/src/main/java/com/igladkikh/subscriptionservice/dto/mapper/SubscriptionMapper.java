package com.igladkikh.subscriptionservice.dto.mapper;

import com.igladkikh.subscriptionservice.dto.SubscriptionRequestDto;
import com.igladkikh.subscriptionservice.dto.SubscriptionResponseDto;
import com.igladkikh.subscriptionservice.model.Subscription;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface SubscriptionMapper {

    Subscription toEntity(SubscriptionRequestDto subscriptionDto);

    SubscriptionResponseDto toResponseDto(Subscription subscription);

    List<SubscriptionResponseDto> toResponseDto(Iterable<Subscription> subscriptions);
}