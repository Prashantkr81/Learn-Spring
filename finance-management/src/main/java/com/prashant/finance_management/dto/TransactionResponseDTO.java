package com.prashant.finance_management.dto;

import com.prashant.finance_management.enums.TransactionType;

import java.time.LocalDate;

public class TransactionResponseDTO {

    private Long id;
    private String title;
    private Double amount;
    private String category;
    private TransactionType type;
    private LocalDate date;
    private String description;

    public TransactionResponseDTO() {
    }

    public TransactionResponseDTO(Long id, String title, Double amount, String category, TransactionType type, LocalDate date, String description) {
        this.id = id;
        this.title = title;
        this.amount = amount;
        this.category = category;
        this.type = type;
        this.date = date;
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
