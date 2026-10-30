package com.projectdarkhope.expenseTracker.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.projectdarkhope.expenseTracker.dto.LoginRequest;
import com.projectdarkhope.expenseTracker.dto.LoginResponse;
import com.projectdarkhope.expenseTracker.dto.UserResponse;
import com.projectdarkhope.expenseTracker.entity.User;
import com.projectdarkhope.expenseTracker.exception.EmailAlreadyExistsException;
import com.projectdarkhope.expenseTracker.exception.InvalidCredentialsException;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.projectdarkhope.expenseTracker.repository.UserRepository;
import com.projectdarkhope.expenseTracker.security.JwtService;

@Service 
public class UserService {

    @Autowired 
    private UserRepository userRepository;

    @Autowired 
    private PasswordEncoder passwordEncoder;

    @Autowired 
    private JwtService jwtService;

    public UserResponse registerUser(User user)
    {
        if( userRepository.existsByEmail(user.getEmail()))
        {
            throw new EmailAlreadyExistsException("Email already registered");
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        User savedUser =  userRepository.save(user);
        return new UserResponse(savedUser.getId(), savedUser.getName(), savedUser.getEmail());

    }

    public LoginResponse loginUser(LoginRequest loginRequest)
    {
        User user = userRepository.findByEmail(loginRequest.getEmail())
            .orElseThrow(()->new InvalidCredentialsException("Invalid email or passowrd"));

        if(!passwordEncoder.matches(loginRequest.getPassword(), user.getPassword()))
        {
            throw new InvalidCredentialsException("Invalid email or password");

        }

        long expirationTime;

        if(loginRequest.isRememberMe())
        {
            expirationTime = 1000L * 60 * 60 * 24 * 30;
        }
        else
        {
            expirationTime = 1000L * 60 * 60;
        }

        String token = jwtService.generateToken(user.getEmail(), expirationTime);

        return new LoginResponse(
            token,
            new UserResponse(user.getId(), user.getName(), user.getEmail())
        );
        
    }
}
