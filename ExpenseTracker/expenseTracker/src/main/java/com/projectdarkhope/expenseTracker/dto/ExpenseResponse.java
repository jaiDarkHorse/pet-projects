package com.projectdarkhope.expenseTracker.dto;

import java.time.LocalDate;

public class ExpenseResponse {

    private Long id;
    private String description;
    private Double amount;
    private String category;
    private LocalDate date;

    public ExpenseResponse(
            Long id,
            String description,
            Double amount,
            String category,
            LocalDate date) {

        this.id = id;
        this.description = description;
        this.amount = amount;
        this.category = category;
        this.date = date;
    }

    public Long getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public Double getAmount() {
        return amount;
    }

    public String getCategory() {
        return category;
    }

    public LocalDate getDate() {
        return date;
    }
}