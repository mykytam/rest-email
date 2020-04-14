package com.email.services;

import com.email.exceptions.UserNotFoundException;
import com.email.models.User;
import com.email.repositories.UserRepository;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{
    @Autowired
    private UserRepository userRepository;

    public String generatePassword() {
        return RandomStringUtils.randomAlphabetic(10);
    }

    public String generateEmail(User user) {
        return user.getFirstName().toLowerCase() + "." + user.getLastName().toLowerCase()
                + "@" + user.getDepartment() + ".companyname.com";
    }

    @Override
    public User addUser(User user) {
        //User userFromDb = userRepository.findById(user.getUserId()).orElseThrow(() -> new UserNotFoundException("User not found"));
        user.setPassword(generatePassword());
        user.setEmail(generateEmail(user));
        user.setDepartment(user.getDepartment());
        userRepository.save(user);
        return user;
    }

}
