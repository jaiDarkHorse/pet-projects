package com.darkhope.ToDoList.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.rememberme.PersistentTokenBasedRememberMeServices;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class CustomRememberMeServices
        extends PersistentTokenBasedRememberMeServices {

    public CustomRememberMeServices(
            String key,
            UserDetailsService userDetailsService,
            PersistentTokenRepository tokenRepository) {

        super(
            key,
            userDetailsService,
            tokenRepository
        );
    }

    public void createRememberMeCookie(
            HttpServletRequest request,
            HttpServletResponse response,
            Authentication authentication) {

        super.onLoginSuccess(
            request,
            response,
            authentication
        );
    }
}