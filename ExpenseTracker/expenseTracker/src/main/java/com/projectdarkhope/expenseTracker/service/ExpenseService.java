package com.projectdarkhope.expenseTracker.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.projectdarkhope.expenseTracker.dto.ExpenseRequest;
import com.projectdarkhope.expenseTracker.dto.ExpenseResponse;
import com.projectdarkhope.expenseTracker.entity.Expense;
import com.projectdarkhope.expenseTracker.entity.User;
import com.projectdarkhope.expenseTracker.repository.ExpenseRepository;
import com.projectdarkhope.expenseTracker.repository.UserRepository;

@Service
public class ExpenseService {
    @Autowired
    private ExpenseRepository expenseRepository;

    @Autowired
    private UserRepository userRepository;

    private User getCurrentUser() {

        String email = SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getName();

        return userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    public ExpenseResponse createExpense(ExpenseRequest request) {
        User user = getCurrentUser();
        Expense expense = new Expense();

        expense.setDescription(request.getDescription());
        expense.setAmount(request.getAmount());
        expense.setCategory(request.getCategory());
        expense.setDate(request.getDate());
        expense.setUser(user);

        Expense savedExpense = expenseRepository.save(expense);

        return convertToResponse(savedExpense);
    }

    public List<ExpenseResponse> getMyExpenses() {

        User user = getCurrentUser();

        return expenseRepository.findByUser(user)
                .stream()
                .map(this::convertToResponse)
                .toList();
    }

    public ExpenseResponse updateExpense(
            Long id,
            ExpenseRequest request) {

        User user = getCurrentUser();

        Expense expense = expenseRepository
                .findByIdAndUser(id, user)
                .orElseThrow(() -> new RuntimeException("Expense not found"));

        expense.setDescription(request.getDescription());
        expense.setAmount(request.getAmount());
        expense.setCategory(request.getCategory());
        expense.setDate(request.getDate());

        Expense updatedExpense = expenseRepository.save(expense);

        return convertToResponse(updatedExpense);
    }

    public void deleteExpense(Long id) {

        User user = getCurrentUser();

        Expense expense = expenseRepository
                .findByIdAndUser(id, user)
                .orElseThrow(() -> new RuntimeException("Expense not found"));

        expenseRepository.delete(expense);
    }

    private ExpenseResponse convertToResponse(Expense expense) {

        return new ExpenseResponse(
                expense.getId(),
                expense.getDescription(),
                expense.getAmount(),
                expense.getCategory(),
                expense.getDate());
    }
}
