package com.igladkikh.userservice.dto.mapper;

import com.igladkikh.userservice.dto.SubscriptionDto;
import com.igladkikh.userservice.dto.UserRequestDto;
import com.igladkikh.userservice.dto.UserResponseDto;
import com.igladkikh.userservice.model.User;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingTarget;
import org.mapstruct.Named;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.Set;
import java.util.stream.Collectors;

@Component
@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface UserMapper {

    User toEntity(UserRequestDto userRequestDto);

    UserResponseDto toResponseDto(User user);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntityFromDto(UserRequestDto userRequestDto, @MappingTarget User user);


    @Mapping(target = "subscriptionIds", source = "subscriptions", qualifiedByName = "subscriptionsToSubscriptionIds")
    UserResponseDto toResponseDto(User user, Set<SubscriptionDto> subscriptions);

    @Named("subscriptionsToSubscriptionIds")
    default Set<Long> subscriptionsToSubscriptionIds(Set<SubscriptionDto> subscriptions) {
        if (subscriptions == null || subscriptions.isEmpty()) {
            return Collections.emptySet();
        }
        return subscriptions.stream().map(SubscriptionDto::getId).collect(Collectors.toSet());
    }
}