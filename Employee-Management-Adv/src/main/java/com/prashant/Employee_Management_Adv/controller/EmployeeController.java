package com.prashant.Employee_Management_Adv.controller;


import com.prashant.Employee_Management_Adv.dto.EmployeeRequest;
import com.prashant.Employee_Management_Adv.dto.EmployeeResponse;
import com.prashant.Employee_Management_Adv.service.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @PostMapping
    public ResponseEntity<EmployeeResponse> createEmployee(
            @Valid @RequestBody EmployeeRequest request ){

        EmployeeResponse response= employeeService.createEmployee(request);

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<EmployeeResponse>> getAllEmployees(){

        return ResponseEntity.ok(employeeService.getAllEmployees());

    }

    @GetMapping("/{id}")
    public ResponseEntity<EmployeeResponse> getEmployeeById(@PathVariable Long id){

        return ResponseEntity.ok(employeeService.getEmployeeById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<EmployeeResponse> updateEmployee(
            @PathVariable Long id,
            @Valid @RequestBody EmployeeRequest request){

        return ResponseEntity.ok(employeeService.update(id, request));
    }

    // Delete Employee
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEmployee(
            @PathVariable Long id) {

        employeeService.deleteEmployee(id);

        return ResponseEntity.noContent().build();
    }

    // Find By Department
    @GetMapping("/department/{department}")
    public ResponseEntity<List<EmployeeResponse>> getEmployeesByDepartment(
            @PathVariable String department) {

        return ResponseEntity.ok(
                employeeService.getEmployeeByDepartment(department)
        );
    }

    // Find Salary Greater Than
    @GetMapping("/salary")
    public ResponseEntity<List<EmployeeResponse>> getEmployeesBySalary(
            @RequestParam Double salary) {

        return ResponseEntity.ok(
                employeeService.getEmployeeBySalary(salary)
        );
    }

    // Search Employee
    @GetMapping("/search")
    public ResponseEntity<List<EmployeeResponse>> searchEmployee(
            @RequestParam String keyword) {

        return ResponseEntity.ok(
                employeeService.searchEmployee(keyword)
        );
    }

    // Salary Between
    @GetMapping("/salary-range")
    public ResponseEntity<List<EmployeeResponse>> getEmployeesBySalaryRange(
            @RequestParam Double minSalary,
            @RequestParam Double maxSalary) {

        return ResponseEntity.ok(
                employeeService.getEmployeesBySalaryRange(minSalary, maxSalary)
        );
    }

    // Sort By Salary
    @GetMapping("/sort/salary")
    public ResponseEntity<List<EmployeeResponse>> getEmployeesSortedBySalary() {

        return ResponseEntity.ok(
                employeeService.getEmployeesSortedBySalary()
        );
    }
}
