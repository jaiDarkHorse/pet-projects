package com.projectdarkhope.expenseTracker.controller;

import com.projectdarkhope.expenseTracker.dto.ExpenseRequest;
import com.projectdarkhope.expenseTracker.dto.ExpenseResponse;
import com.projectdarkhope.expenseTracker.service.ExpenseService;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/expenses")
public class ExpenseController {

    @Autowired
    private ExpenseService expenseService;


    @PostMapping
    public ExpenseResponse createExpense(
            @Valid @RequestBody ExpenseRequest request) {

        return expenseService.createExpense(request);
    }


    @GetMapping
    public List<ExpenseResponse> getMyExpenses() {

        return expenseService.getMyExpenses();
    }


    @PutMapping("/{id}")
    public ExpenseResponse updateExpense(
            @PathVariable Long id,
            @Valid @RequestBody ExpenseRequest request) {

        return expenseService.updateExpense(id, request);
    }


    @DeleteMapping("/{id}")
    public void deleteExpense(@PathVariable Long id) {

        expenseService.deleteExpense(id);
    }
}