package com.igladkikh.usersubscription.service;

import com.igladkikh.usersubscription.dto.UserRequestDto;
import com.igladkikh.usersubscription.dto.UserResponseDto;
import com.igladkikh.usersubscription.model.User;

public interface UserService {

    UserResponseDto findById(Long id);

    UserResponseDto create(UserRequestDto dto);

    UserResponseDto update(Long id, UserRequestDto dto);

    void delete(Long id);

    User findEntity(Long id);
}
