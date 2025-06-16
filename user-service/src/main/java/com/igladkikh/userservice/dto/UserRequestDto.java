package com.igladkikh.userservice.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import com.igladkikh.userservice.util.Marker;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserRequestDto {
    @NotBlank(message = "Name is required", groups = Marker.OnCreate.class)
    private String name;
    @Email(message = "Email should be valid")
    @NotBlank(message = "Email is required", groups = Marker.OnCreate.class)
    private String email;
}