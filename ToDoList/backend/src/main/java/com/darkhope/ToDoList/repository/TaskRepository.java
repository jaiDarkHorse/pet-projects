package com.darkhope.ToDoList.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.darkhope.ToDoList.model.Task;
import com.darkhope.ToDoList.model.User;

public interface TaskRepository extends JpaRepository<Task,Long>
{
	List<Task> findByUser_Id(Long userId);
	
	void deleteByUser_Id(Long userId);
}