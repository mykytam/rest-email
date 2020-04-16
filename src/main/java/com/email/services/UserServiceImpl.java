package com.email.services;

import com.email.dto.UserCreateDto;
import com.email.dto.UserResponseDto;
import com.email.dto.UserUpdateDto;
import com.email.exceptions.UserNotFoundException;
import com.email.models.User;
import com.email.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;

    @Override
    public UserResponseDto findById(Long id) {
        User user = getUser(id);
        return buildResponse(user);
    }

    @Override
    public void deleteById(Long id) {
        userRepository.delete(getUser(id));
    }

    @Override
    public UserResponseDto addUser(UserCreateDto userCreateDto) {
        User user = new User();
        user.setFirstName(userCreateDto.getFirstName());
        user.setLastName(userCreateDto.getLastName());
        user.setDepartment(userCreateDto.getDepartment());

        user.setPassword(generatePassword());
        user.setEmail(generateEmail(user));
        user.setDepartment(user.getDepartment());

        User savedUser = userRepository.save(user);

        return buildResponse(savedUser);
    }

    @Override
    public List<UserResponseDto> findAll() {
        return userRepository.findAll().stream().map(this::buildResponse).collect(Collectors.toList());
    }

    @Override
    public UserUpdateDto update(Long id, User user) {
        User existingSession = getUser(id);
        BeanUtils.copyProperties(user, existingSession, "user_id");
        User updateUser = userRepository.save(existingSession);

        return UserUpdateDto.builder()
                .firstName(updateUser.getFirstName())
                .lastName(updateUser.getLastName())
                .department(updateUser.getDepartment())
                .email(updateUser.getEmail())
                .build();
    }

    private User getUser(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User not found"));
    }

    private UserResponseDto buildResponse(User savedUser) {
        return UserResponseDto.builder()
                .userId(savedUser.getUserId())
                .firstName(savedUser.getFirstName())
                .lastName(savedUser.getLastName())
                .department(savedUser.getDepartment())
                .email(savedUser.getEmail())
                .build();
    }

    public String generatePassword() {
        return RandomStringUtils.randomAlphabetic(10);
    }

    public String generateEmail(User user) {
        return user.getFirstName().toLowerCase() + "." + user.getLastName().toLowerCase()
                + "@" + user.getDepartment() + ".companyname.com";
    }
}