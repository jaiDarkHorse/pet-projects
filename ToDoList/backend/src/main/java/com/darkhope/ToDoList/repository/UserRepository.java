package com.darkhope.ToDoList.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.darkhope.ToDoList.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>
{
	Optional<User> findByGoogleId(String googleId);
}