package com.darkhope.ToDoList.security;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

@Configuration
public class RememberMeConfig {

    @Bean
    public PersistentTokenRepository persistentTokenRepository(
            DataSource dataSource) {

        JdbcTokenRepositoryImpl repository =
                new JdbcTokenRepositoryImpl();

        repository.setDataSource(dataSource);

        return repository;
    }

    @Bean
    public CustomRememberMeServices customRememberMeServices(
            PersistentTokenRepository persistentTokenRepository,
            UserDetailsService userDetailsService) {

        return new CustomRememberMeServices(
                "myRememberMeKey",
                userDetailsService,
                persistentTokenRepository
        );
    }
}