package com.prashant.Employee_Management_System.controller;


import com.prashant.Employee_Management_System.entity.Employee;
import com.prashant.Employee_Management_System.service.EmployeeService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employee")
public class EmployeeController {
    private final EmployeeService employeeService;

    EmployeeController(EmployeeService employeeService){
        this.employeeService= employeeService;
    }

    @PostMapping
    public Employee addEmployee(@RequestBody Employee employee){
        return employeeService.addEmployee(employee);
    }

    @GetMapping
    public List<Employee> getAllEmployees(){
        return employeeService.getAllEmployee();
    }

    @GetMapping("/{id}")
    public Employee getEmployeeById(@PathVariable Long id){
        return employeeService.getEmployeeById(id);
    }

    @PutMapping("/{id}")
    public Employee updateEmployee(@PathVariable Long id, @RequestBody Employee employee){
        return employeeService.updateEmployee(id, employee);
    }


    @DeleteMapping("/{id}")
    public String deleteEmployee(@PathVariable Long id) {
        employeeService.deleteEmployee(id);

        return "Student Deleted Successfull" ;
    }


    @GetMapping("/salaryMoreThan/{salary}")
    public List<Employee> getEmployeeSalaryMoreThan(@PathVariable double salary){

        return employeeService.getEmployeesWithSalaryGreaterThan(salary);

    }

}
