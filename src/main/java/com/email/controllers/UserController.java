package com.email.controllers;

import com.email.dto.UserCreateDto;
import com.email.dto.UserResponseDto;
import com.email.dto.UserUpdateDto;
import com.email.models.User;
import com.email.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    // get all
    @GetMapping
    public List<UserResponseDto> list() {
        return userService.findAll();
    }

    //get by id
    @GetMapping("{id}")
    public UserResponseDto get(@PathVariable Long id) {
        return userService.findById(id);
    }

    // create
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserResponseDto create(@RequestBody final UserCreateDto user) {
        return userService.addUser(user);
    }

    // delete
    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete (@PathVariable Long id) {
        userService.deleteById(id);
    }

    // update
    @PutMapping("{id}")
    public UserUpdateDto update(@PathVariable Long id, @RequestBody User user) {
        return userService.update(id, user);
    }
}
