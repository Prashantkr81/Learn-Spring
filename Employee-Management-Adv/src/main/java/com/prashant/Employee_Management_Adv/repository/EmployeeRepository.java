package com.prashant.Employee_Management_Adv.repository;

import com.prashant.Employee_Management_Adv.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {


    List<Employee> findByDepartment(String department);

    List<Employee> findBySalaryGreaterThan(Double salary);

    List<Employee> findByNameContaining(String keyword);

    List<Employee> findBySalaryBetween(Double minSalary, Double maxSalary);

    List<Employee> findAllByOrderBySalaryDesc();

    List<Employee> findAllByOrderBySalaryAsc();

    Employee findByEmail(String email);

    boolean existsByEmail(String email);

    void deleteByEmail(String email);

    long countByDepartment(String department);
}
