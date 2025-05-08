package com.igladkikh.usersubscription.dto.mapper;

import com.igladkikh.usersubscription.dto.UserRequestDto;
import com.igladkikh.usersubscription.dto.UserResponseDto;
import com.igladkikh.usersubscription.model.Subscription;
import com.igladkikh.usersubscription.model.User;
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

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntityFromDto(UserRequestDto userRequestDto, @MappingTarget User user);

    @Mapping(target = "subscriptionIds", source = "subscriptions", qualifiedByName = "subscriptionsToSubscriptionIds")
    UserResponseDto toResponseDto(User user);

    @Named("subscriptionsToSubscriptionIds")
    default Set<Long> subscriptionsToSubscriptionIds(Set<Subscription> subscriptions) {
        if (subscriptions == null || subscriptions.isEmpty()) {
            return Collections.emptySet();
        }
        return subscriptions.stream().map(Subscription::getId).collect(Collectors.toSet());
    }
}