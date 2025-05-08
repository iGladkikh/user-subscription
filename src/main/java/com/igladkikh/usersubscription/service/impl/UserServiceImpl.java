package com.igladkikh.usersubscription.service.impl;

import com.igladkikh.usersubscription.dto.UserRequestDto;
import com.igladkikh.usersubscription.dto.UserResponseDto;
import com.igladkikh.usersubscription.dto.mapper.UserMapper;
import com.igladkikh.usersubscription.exception.AppException;
import com.igladkikh.usersubscription.model.User;
import com.igladkikh.usersubscription.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class UserServiceImpl implements com.igladkikh.usersubscription.service.UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    public UserResponseDto findById(Long id) {
        return userMapper.toResponseDto(findEntity(id));
    }

    @Override
    public UserResponseDto create(UserRequestDto dto) {
        User user = userMapper.toEntity(dto);
        User resultUser = userRepository.save(user);
        return userMapper.toResponseDto(resultUser);
    }

    @Override
    public UserResponseDto update(Long id, UserRequestDto dto) {
        User user = findEntity(id);
        userMapper.updateEntityFromDto(dto, user);
        User resultUser = userRepository.save(user);
        return userMapper.toResponseDto(resultUser);
    }

    @Override
    public void delete(Long id) {
        userRepository.delete(findEntity(id));
    }

    @Override
    @Transactional(propagation = Propagation.MANDATORY)
    public User findEntity(Long id) {
        return userRepository.findById(id).orElseThrow(() ->
                new AppException(HttpStatus.NOT_FOUND, "User with id `%s` not found".formatted(id)));
    }
}
