package com.prashant.Employee_Management_Adv.service;

import com.prashant.Employee_Management_Adv.dto.EmployeeRequest;
import com.prashant.Employee_Management_Adv.dto.EmployeeResponse;
import com.prashant.Employee_Management_Adv.entity.Employee;
import com.prashant.Employee_Management_Adv.repository.EmployeeRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class EmployeeService {

    private final EmployeeRepository EmployeeRepo;

    public EmployeeService(EmployeeRepository EmployeeRepo){
        this.EmployeeRepo= EmployeeRepo;
    }

    public EmployeeResponse createEmployee(EmployeeRequest request){
        Employee employee = mapToEntity(request);

        Employee savedEmployee = EmployeeRepo.save(employee);

        return mapToResponse(savedEmployee);
    }

    public List<EmployeeResponse> getAllEmployees() {

        List<Employee> employees = EmployeeRepo.findAll();

        List<EmployeeResponse> responseList = new ArrayList<>();

        for(Employee employee: employees) responseList.add(mapToResponse(employee));

        return responseList;
    }

    public EmployeeResponse getEmployeeById(Long id){

        Employee employee= EmployeeRepo.findById(id).orElse(null);

        if (employee == null) return null;

        return mapToResponse(employee);
    }

    public EmployeeResponse update(Long id, EmployeeRequest request){

        Employee employee= EmployeeRepo.findById(id).orElse(null);

        if(employee == null) return null;

        mapToEntity(request, employee);

        Employee updatedEmployee = EmployeeRepo.save(employee);

        return mapToResponse(updatedEmployee);

     }

    public void deleteEmployee(Long id){
        EmployeeRepo.deleteById(id);
    }

    public List<EmployeeResponse> getEmployeeByDepartment(String department){

        List<Employee> employees = EmployeeRepo.findByDepartment(department);

        List<EmployeeResponse> responseList= new ArrayList<>();

        for(Employee employee: employees)
            responseList.add(mapToResponse(employee));

        return responseList;
    }

    public List<EmployeeResponse> getEmployeeBySalary(Double salary){

        List<Employee> employees= EmployeeRepo.findBySalaryGreaterThan(salary);

        List<EmployeeResponse> responseList = new ArrayList<>();

        for(Employee employee: employees) responseList.add(mapToResponse(employee));

        return responseList;

    }

    public List<EmployeeResponse> getEmployeesBySalaryRange(Double minSalary, Double maxSalary) {

        List<Employee> employees = EmployeeRepo.findBySalaryBetween(minSalary, maxSalary);

        List<EmployeeResponse> responseList = new ArrayList<>();

        for (Employee employee : employees) {
            responseList.add(mapToResponse(employee));
        }

        return responseList;
    }

    public List<EmployeeResponse> getEmployeesSortedBySalary() {

        List<Employee> employees = EmployeeRepo.findAllByOrderBySalaryDesc();

        List<EmployeeResponse> responseList = new ArrayList<>();

        for (Employee employee : employees) {
            responseList.add(mapToResponse(employee));
        }

        return responseList;
    }

    public List<EmployeeResponse> searchEmployee(String keyword) {

        List<Employee> employees = EmployeeRepo.findByNameContaining(keyword);

        List<EmployeeResponse> responseList = new ArrayList<>();

        for (Employee employee : employees) {
            responseList.add(mapToResponse(employee));
        }

        return responseList;
    }

    // ------------------ Helper Methods ------------------

    private Employee mapToEntity(EmployeeRequest request) {

        Employee employee = new Employee();

        employee.setName(request.getName());
        employee.setEmail(request.getEmail());
        employee.setDepartment(request.getDepartment());
        employee.setSalary(request.getSalary());
        employee.setJoiningDate(request.getJoiningDate());

        return employee;
    }

    private void mapToEntity(EmployeeRequest request, Employee employee){

        employee.setName(request.getName());
        employee.setEmail(request.getName());
        employee.setDepartment(request.getDepartment());
        employee.setSalary(request.getSalary());
        employee.setJoiningDate(request.getJoiningDate());
    }

    private EmployeeResponse mapToResponse(Employee employee) {

        EmployeeResponse response = new EmployeeResponse();

        response.setId(employee.getId());
        response.setName(employee.getName());
        response.setEmail(employee.getEmail());
        response.setDepartment(employee.getDepartment());
        response.setSalary(employee.getSalary());
        response.setJoiningDate(employee.getJoiningDate());

        return response;
    }

}
