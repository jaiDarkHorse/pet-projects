package com.darkhope.ToDoList.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.darkhope.ToDoList.model.Task;
import com.darkhope.ToDoList.model.User;
import com.darkhope.ToDoList.repository.TaskRepository;
import com.darkhope.ToDoList.repository.UserRepository;

import jakarta.transaction.Transactional;

@Service
public class TaskService
{
	@Autowired
	private TaskRepository taskRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	public List<Task> getTasksByGoogleId(String googleId)
	{
		User user = userRepository.findByGoogleId(googleId).orElseThrow();
		return taskRepository.findByUser_Id(user.getId());
		
	}
	
	public Task createTask(Task task, String googleId)
	{
		User user = userRepository.findByGoogleId(googleId).orElseThrow();
		task.setUser(user);
		
		return taskRepository.save(task);
	}
	
	public Task updateTask(Long taskId, Task updatedTask, String googleId) {
		User user = userRepository
	            .findByGoogleId(googleId)
	            .orElseThrow();

	    Task task = taskRepository
	            .findById(taskId)
	            .orElseThrow();
	    
	    if (!task.getUser().getId().equals(user.getId())) {
	        throw new RuntimeException("You cannot update this task");
	    }
	    task.setName(updatedTask.getName());
	    task.setCompleted(updatedTask.isCompleted());

	    return taskRepository.save(task);
	    
	}
	
	public void deleteTask(Long taskId, String googleId) {

	    User user = userRepository
	            .findByGoogleId(googleId)
	            .orElseThrow();

	    Task task = taskRepository
	            .findById(taskId)
	            .orElseThrow();

	    if (!task.getUser().getId().equals(user.getId())) {
	        throw new RuntimeException("You cannot delete this task");
	    }

	    taskRepository.delete(task);
	}
	
	@Transactional
	public void deleteAllTasks(String googleId) 
	{

	    User user = userRepository
	            .findByGoogleId(googleId)
	            .orElseThrow();

	    taskRepository.deleteByUser_Id(user.getId());
	}
}