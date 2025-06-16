package com.igladkikh.userservice.service.impl;

import com.igladkikh.userservice.dto.UserRequestDto;
import com.igladkikh.userservice.dto.UserResponseDto;
import com.igladkikh.userservice.dto.mapper.UserMapper;
import com.igladkikh.userservice.exception.AppException;
import com.igladkikh.userservice.model.User;
import com.igladkikh.userservice.repository.UserRepository;
import com.igladkikh.userservice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import com.igladkikh.userservice.util.CacheUtil;

@Service
@Transactional
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final CacheUtil cacheUtil;

    @Override
    @Cacheable(value = CacheUtil.USER_CACHE_ID, key = "#id")
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
    @CacheEvict(value = CacheUtil.USER_CACHE_ID, key = "#id")
    public UserResponseDto update(Long id, UserRequestDto dto) {
        User user = findEntity(id);
        userMapper.updateEntityFromDto(dto, user);
        User resultUser = userRepository.save(user);
        return userMapper.toResponseDto(resultUser);
    }

    @Override
    @CacheEvict(value = CacheUtil.USER_CACHE_ID, key = "#id")
    public void delete(Long id) {
        userRepository.delete(findEntity(id));
        // Очищаем связанный кэш
        cacheUtil.evictUserSubscriptionsCache(id);
        cacheUtil.clearTopSubscriptionCache();
    }

    @Override
    @Transactional(propagation = Propagation.MANDATORY)
    public User findEntity(Long id) {
        return userRepository.findById(id).orElseThrow(() ->
                new AppException(HttpStatus.NOT_FOUND, "User with id `%s` not found".formatted(id)));
    }
}
