package com.darkhope.ToDoList.security;

import java.io.IOException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.darkhope.ToDoList.model.User;
import com.darkhope.ToDoList.repository.UserRepository;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class OAuth2SuccessHandler
        implements AuthenticationSuccessHandler {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CustomRememberMeServices rememberMeServices;

    @Override
    public void onAuthenticationSuccess(
            HttpServletRequest request,
            HttpServletResponse response,
            Authentication authentication)
            throws IOException, ServletException {

        OAuth2User oauth2User =
                (OAuth2User) authentication.getPrincipal();

        String googleId =
                oauth2User.getAttribute("sub");

        String name =
                oauth2User.getAttribute("name");

        String email =
                oauth2User.getAttribute("email");

        System.out.println(
                "Google ID: " + googleId
        );

        System.out.println(
                "Name: " + name
        );

        System.out.println(
                "Email: " + email
        );

        Optional<User> existingUser =
                userRepository.findByGoogleId(googleId);

        if (existingUser.isEmpty()) {

            User user = new User();

            user.setGoogleId(googleId);
            user.setEmail(email);
            user.setName(name);

            userRepository.save(user);
        }

        // Create remember-me cookie
        rememberMeServices.createRememberMeCookie(
                request,
                response,
                authentication
        );

        // Redirect to React
        response.sendRedirect(
                "http://localhost:5500/TasksToDo.html"
        );
    }
}