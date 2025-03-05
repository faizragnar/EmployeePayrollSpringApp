package org.example.employeepayrollspringapp.repository;

import org.example.employeepayrollspringapp.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
}