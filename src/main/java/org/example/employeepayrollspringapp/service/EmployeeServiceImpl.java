package org.example.employeepayrollspringapp.service;

import org.example.employeepayrollspringapp.dto.EmployeeDTO;
import java.util.stream.Collectors;

import org.example.employeepayrollspringapp.model.Employee;
import org.example.employeepayrollspringapp.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    EmployeeRepository employeeRepository;

    private Employee convertToEntity(EmployeeDTO employeeDTO) {
        return new Employee(0, employeeDTO.getName(), employeeDTO.getDepartment(), employeeDTO.getSalary());
    }

    private EmployeeDTO convertToDTO(Employee employee) {
        return new EmployeeDTO(employee.getName(), employee.getDepartment(), employee.getSalary());
    }

    @Override
    public List<EmployeeDTO> getAllEmployees() {
        return employeeRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public EmployeeDTO getEmployeeById(int id) {
        return employeeRepository.findById(id)
                .map(this::convertToDTO)
                .orElseThrow(() -> new RuntimeException("Employee not found!"));
    }

    @Override
    public EmployeeDTO addEmployee(EmployeeDTO employeeDTO) {
        Employee savedEmployee = employeeRepository.save(convertToEntity(employeeDTO));
        return convertToDTO(savedEmployee);
    }

    @Override
    public EmployeeDTO updateEmployee(int id, EmployeeDTO updatedEmployee) {
        Employee existingEmployee = employeeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Employee not found!"));

        existingEmployee.setName(updatedEmployee.getName());
        existingEmployee.setDepartment(updatedEmployee.getDepartment());
        existingEmployee.setSalary(updatedEmployee.getSalary());

        Employee savedEmployee = employeeRepository.save(existingEmployee);
        return convertToDTO(savedEmployee);
    }

    @Override
    public void deleteEmployee(int id) {
        employeeRepository.deleteById(id);
    }


}