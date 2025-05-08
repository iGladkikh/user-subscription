package com.igladkikh.usersubscription.dto;

import lombok.Data;

import java.util.Set;

@Data
public class UserResponseDto {
    private Long id;
    private String name;
    private String email;
    private Set<Long> subscriptionIds;
}