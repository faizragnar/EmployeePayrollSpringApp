package org.example.employeepayrollspringapp.service;

import org.example.employeepayrollspringapp.dto.EmployeeDTO;

import java.util.List;

public interface EmployeeService {
    List<EmployeeDTO> getAllEmployees();
    EmployeeDTO getEmployeeById(int id);
    EmployeeDTO addEmployee(EmployeeDTO employeeDTO);
    EmployeeDTO updateEmployee(int id, EmployeeDTO updatedEmployee);
    void deleteEmployee(int id);
}
