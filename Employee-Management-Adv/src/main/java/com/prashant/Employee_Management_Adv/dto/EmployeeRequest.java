package com.prashant.Employee_Management_Adv.dto;

import jakarta.persistence.Entity;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

import java.time.LocalDate;

public class EmployeeRequest {

    @NotBlank(message = "Name can't be empty")
    private String name;

    @Email(message = "Invalid email")
    @NotBlank(message = "Email can't be empty")
    private String email;

    @NotBlank(message = "Department can't be blank")
    private String department;

    @Positive(message = "Salary must be greater than zero")
    private Double salary;

    private LocalDate joiningDate;

    public EmployeeRequest(){
    }

    public EmployeeRequest(String name, String email, String department, Double salary, LocalDate joiningDate) {
        this.name = name;
        this.email = email;
        this.department = department;
        this.salary = salary;
        this.joiningDate = joiningDate;
    }

    public @NotBlank(message = "Name can't be empty") String getName() {
        return name;
    }

    public void setName(@NotBlank(message = "Name can't be empty") String name) {
        this.name = name;
    }

    public @Email(message = "Invalid email") @NotBlank(message = "Email can't be empty") String getEmail() {
        return email;
    }

    public void setEmail(@Email(message = "Invalid email") @NotBlank(message = "Email can't be empty") String email) {
        this.email = email;
    }

    public @NotBlank(message = "Department can't be blank") String getDepartment() {
        return department;
    }

    public void setDepartment(@NotBlank(message = "Department can't be blank") String department) {
        this.department = department;
    }

    public @Positive(message = "Salary must be greater than zero") Double getSalary() {
        return salary;
    }

    public void setSalary(@Positive(message = "Salary must be greater than zero") Double salary) {
        this.salary = salary;
    }

    public LocalDate getJoiningDate() {
        return joiningDate;
    }

    public void setJoiningDate(LocalDate joiningDate) {
        this.joiningDate = joiningDate;
    }
}
