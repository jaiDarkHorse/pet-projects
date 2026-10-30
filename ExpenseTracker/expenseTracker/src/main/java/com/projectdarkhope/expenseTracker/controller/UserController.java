package com.projectdarkhope.expenseTracker.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.projectdarkhope.expenseTracker.dto.LoginRequest;
import com.projectdarkhope.expenseTracker.dto.LoginResponse;
import com.projectdarkhope.expenseTracker.dto.UserResponse;
import com.projectdarkhope.expenseTracker.entity.User;
import com.projectdarkhope.expenseTracker.service.UserService;

import jakarta.validation.Valid;

@RestController 
@RequestMapping("/api/auth")
public class UserController {
    
    @Autowired 
    private UserService userService;

    @PostMapping("/register")
    public UserResponse registerUser(@Valid @RequestBody User user)
    {
        return userService.registerUser(user);
    }

    @PostMapping("/login")
    public LoginResponse loginUser(@Valid @RequestBody LoginRequest loginRequest)
    {
        return userService.loginUser(loginRequest);
    }
}
