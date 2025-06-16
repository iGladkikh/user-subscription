package com.igladkikh.userservice.controller;

import com.igladkikh.userservice.annotation.LogMethodArgs;
import com.igladkikh.userservice.openapi.UserControllerApi;
import com.igladkikh.userservice.dto.UserRequestDto;
import com.igladkikh.userservice.dto.UserResponseDto;
import com.igladkikh.userservice.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import com.igladkikh.userservice.util.Marker;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController implements UserControllerApi {
    private final UserService userService;

    @Override
    @GetMapping("/{id}")
    public UserResponseDto findById(@PathVariable Long id) {
        return userService.findById(id);
    }

    @Override
    @LogMethodArgs
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Validated({Marker.OnCreate.class})
    public UserResponseDto create(@RequestBody @Valid UserRequestDto dto) {
        return userService.create(dto);
    }

    @Override
    @LogMethodArgs
    @PutMapping("/{id}")
    public UserResponseDto update(@PathVariable Long id, @RequestBody @Valid UserRequestDto dto) {
        return userService.update(id, dto);
    }

    @Override
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        userService.delete(id);
    }
}
