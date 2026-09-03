package com.darkhope.ToDoList.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.darkhope.ToDoList.model.Task;
import com.darkhope.ToDoList.service.TaskService;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {

    @Autowired
    private TaskService taskService;


    // GET ALL TASKS
    @GetMapping
    public List<Task> getTasksByGoogleId(
            Authentication authentication) {

        String googleId = getGoogleId(authentication);

        return taskService.getTasksByGoogleId(googleId);
    }


    // CREATE TASK
    @PostMapping
    public Task createTask(
            @RequestBody Task task,
            Authentication authentication) {

        String googleId = getGoogleId(authentication);

        return taskService.createTask(
                task,
                googleId
        );
    }


    // UPDATE TASK
    @PutMapping("/{id}")
    public Task updateTask(
            @PathVariable Long id,
            @RequestBody Task task,
            Authentication authentication) {

        String googleId = getGoogleId(authentication);

        return taskService.updateTask(
                id,
                task,
                googleId
        );
    }


    // DELETE ONE TASK
    @DeleteMapping("/{id}")
    public void deleteTask(
            @PathVariable Long id,
            Authentication authentication) {

        String googleId = getGoogleId(authentication);

        taskService.deleteTask(
                id,
                googleId
        );
    }


    // DELETE ALL TASKS
    @DeleteMapping
    public void deleteAllTasks(
            Authentication authentication) {

        String googleId = getGoogleId(authentication);

        taskService.deleteAllTasks(googleId);
    }


    // GET GOOGLE ID FROM AUTHENTICATION
    private String getGoogleId(
            Authentication authentication) {

        Object principal =
                authentication.getPrincipal();


        // Normal Google OAuth login
        if (principal instanceof OAuth2User oauth2User) {

            return oauth2User.getAttribute("sub");
        }


        // Remember-me login
        if (principal instanceof UserDetails userDetails) {

            return userDetails.getUsername();
        }


        throw new RuntimeException(
                "Unknown authentication principal"
        );
    }
}