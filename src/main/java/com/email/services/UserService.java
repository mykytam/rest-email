package com.email.services;

import com.email.dto.UserResponseDto;
import com.email.dto.UserCreateDto;
import com.email.dto.UserUpdateDto;
import com.email.models.User;

import java.util.List;

public interface UserService {
    UserResponseDto findById(Long id);

    List<UserResponseDto> findAll();

    void deleteById(Long id);

    UserResponseDto addUser(UserCreateDto userCreateDto);

    UserUpdateDto update(Long id, User user);
}
