package com.prashant.finance_management.dto;

import com.prashant.finance_management.enums.TransactionType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Positive;

import java.time.LocalDate;

public class TransactionRequestDTO {

    @NotBlank(message = "Title required")
    private String title;

    @NotNull(message = "Amount required")
    @Positive(message = "Amount must be positive")
    private Double amount;

    @NotBlank(message = "Category required")
    private String category;

    @NotNull(message = "Transaction type required")
    private TransactionType type;

    @PastOrPresent
    @NotNull(message = "Date required")
    private LocalDate date;


    private String description;

    public TransactionRequestDTO() {
    }

    public TransactionRequestDTO(String title, Double amount, String category, TransactionType type, LocalDate date, String description) {
        this.title = title;
        this.amount = amount;
        this.category = category;
        this.type = type;
        this.date = date;
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public TransactionType getType() {
        return type;
    }

    public void setType(TransactionType type) {
        this.type = type;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
