package com.igladkikh.userservice.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.Set;

@Data
public class UserResponseDto implements Serializable {
    private Long id;
    private String name;
    private String email;
    private Set<Long> subscriptionIds;
}