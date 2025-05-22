package com.igladkikh.usersubscription.dto.mapper;

import com.igladkikh.usersubscription.dto.SubscriptionRequestDto;
import com.igladkikh.usersubscription.dto.SubscriptionResponseDto;
import com.igladkikh.usersubscription.model.Subscription;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface SubscriptionMapper {

    Subscription toEntity(SubscriptionRequestDto subscriptionDto);

    @Mapping(source = "user.id", target = "userId")
    SubscriptionResponseDto toResponseDto(Subscription subscription);

    List<SubscriptionResponseDto> toResponseDto(Iterable<Subscription> subscriptions);
}