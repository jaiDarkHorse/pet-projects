package com.darkhope.ToDoList.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.darkhope.ToDoList.model.User;
import com.darkhope.ToDoList.repository.UserRepository;

@RestController
public class AuthController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/api/auth/me")
    public Map<String, Object> getCurrentUser(
            Authentication authentication) {

        Object principal =
                authentication.getPrincipal();

        String googleId;

        if (principal instanceof OAuth2User oauth2User) {

            googleId =
                    oauth2User.getAttribute("sub");

        } else if (principal instanceof UserDetails userDetails) {

            googleId =
                    userDetails.getUsername();

        } else {

            throw new RuntimeException(
                    "Unknown authentication principal"
            );
        }

        User user = userRepository
                .findByGoogleId(googleId)
                .orElseThrow();

        return Map.of(
            "name", user.getName(),
            "email", user.getEmail(),
            "googleId", user.getGoogleId()
        );
    }
}