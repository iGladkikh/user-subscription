package com.igladkikh.userservice.openapi;

import com.igladkikh.userservice.dto.UserRequestDto;
import com.igladkikh.userservice.dto.UserResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "User controller")
public interface UserControllerApi extends ApiDefinition {

    @Operation(summary = "${api.user.get.summary}")
    UserResponseDto findById(Long id);

    @Operation(summary = "${api.user.create.summary}")
    UserResponseDto create(UserRequestDto dto);

    @Operation(summary = "${api.user.update.summary}")
    UserResponseDto update(Long id, UserRequestDto dto);

    @Operation(summary = "${api.user.delete.summary}")
    void delete(Long id);
}
