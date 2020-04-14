package com.email.controllers;

import com.email.models.User;
import com.email.repositories.UserRepository;
import com.email.services.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @GetMapping
    public List<User> list() {
        return userRepository.findAll();
    }

    @GetMapping("{id}")
    public User get(@PathVariable Long id) {
        return userRepository.getOne(id);
    }

    // create
    @PostMapping
    public User create(@RequestBody final User user) {
        return userService.addUser(user);
    }

    // delete
    @DeleteMapping("{id}")
    public void delete (@PathVariable Long id) {
        userRepository.deleteById(id);
    }

    // update
    @PutMapping("{id}")
    public User update(@PathVariable Long id, @RequestBody User user) {
        User existingSession = userRepository.getOne(id);
        BeanUtils.copyProperties(user, existingSession, "user_id");
        return userRepository.save(existingSession);
    }
}
