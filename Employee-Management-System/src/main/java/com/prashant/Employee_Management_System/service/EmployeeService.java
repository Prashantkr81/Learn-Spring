package com.prashant.Employee_Management_System.service;

import com.prashant.Employee_Management_System.entity.Employee;
import com.prashant.Employee_Management_System.repository.EmployeeRepository;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepo;

    public EmployeeService(EmployeeRepository employeeRepo) {
        this.employeeRepo = employeeRepo;
    }

    public Employee addEmployee(Employee employee){

        if(employee.getSalary() < 0)
            throw new RuntimeException("Salary can't be negetive");
        if(employee.getName().isBlank())
            throw new RuntimeException("Name can't be blank");
        return employeeRepo.save(employee);
    }

    public List<Employee> getAllEmployee(){
        return employeeRepo.findAll();
    }

    public Employee getEmployeeById(Long id){
        return employeeRepo
                .findById(id)
                .orElseThrow(()-> new RuntimeException("Employee Not Found!"));
    }
    public void deleteEmployee(Long id) {
        employeeRepo.deleteById(id);
    }

    public Employee updateEmployee(Long id, Employee updatedemployee){

        Employee employee= employeeRepo.findById(id)
                .orElseThrow(()-> new RuntimeException("Employee not found"));

        employee.setName(updatedemployee.getName());
        employee.setDepartment(updatedemployee.getDepartment());
        employee.setSalary(updatedemployee.getSalary());

        return employeeRepo.save(employee);
    }

    public List<Employee> getEmployeesWithSalaryGreaterThan(double salary) {
        return employeeRepo.findBySalaryGreaterThan(salary);
    }

}
