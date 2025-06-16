package com.igladkikh.userservice.service;

import com.igladkikh.userservice.dto.UserRequestDto;
import com.igladkikh.userservice.dto.UserResponseDto;
import com.igladkikh.userservice.model.User;

public interface UserService {

    UserResponseDto findById(Long id);

    UserResponseDto create(UserRequestDto dto);

    UserResponseDto update(Long id, UserRequestDto dto);

    void delete(Long id);

    User findEntity(Long id);
}
