package org.example.employeepayrollspringapp.service;

import lombok.extern.slf4j.Slf4j;
import org.example.employeepayrollspringapp.dto.EmployeeDTO;
import org.example.employeepayrollspringapp.model.Employee;
import org.example.employeepayrollspringapp.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    EmployeeRepository employeeRepository;

    private Employee convertToEntity(EmployeeDTO employeeDTO) {
        return new Employee(0, employeeDTO.getName(), employeeDTO.getDepartment(), employeeDTO.getSalary());
    }

    private EmployeeDTO convertToDTO(Employee employee) {
        return new EmployeeDTO(employee.getId(), employee.getName(), employee.getDepartment(), employee.getSalary());
    }

    @Override
    public List<EmployeeDTO> getAllEmployees() {
        log.info("Fetching all employees...");
        return employeeRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public EmployeeDTO getEmployeeById(int id) {
        log.info("Fetching employee with ID: {}", id);
        return employeeRepository.findById(id)
                .map(this::convertToDTO)
                .orElseThrow(() -> {
                    log.error("Employee with ID {} not found!", id);
                    return new RuntimeException("Employee not found!");
                });
    }

    @Override
    public EmployeeDTO addEmployee(EmployeeDTO employeeDTO) {
        log.info("Adding new employee: {}", employeeDTO.getName());
        Employee savedEmployee = employeeRepository.save(convertToEntity(employeeDTO));
        log.info("Employee added with ID: {}", savedEmployee.getId());
        return convertToDTO(savedEmployee);
    }

    @Override
    public EmployeeDTO updateEmployee(int id, EmployeeDTO updatedEmployee) {
        log.info("Updating employee with ID: {}", id);
        Employee existingEmployee = employeeRepository.findById(id)
                .orElseThrow(() -> {
                    log.error("Employee with ID {} not found for update!", id);
                    return new RuntimeException("Employee not found!");
                });

        existingEmployee.setName(updatedEmployee.getName());
        existingEmployee.setDepartment(updatedEmployee.getDepartment());
        existingEmployee.setSalary(updatedEmployee.getSalary());

        Employee savedEmployee = employeeRepository.save(existingEmployee);
        log.info("Updated employee with ID: {}", savedEmployee.getId());
        return convertToDTO(savedEmployee);
    }

    @Override
    public void deleteEmployee(int id) {
        log.warn("Deleting employee with ID: {}", id);
        if (!employeeRepository.existsById(id)) {
            log.error("Employee with ID {} not found to delete!", id);
            throw new RuntimeException("Employee not found!");
        }
        employeeRepository.deleteById(id);
        log.info("Deleted employee with ID: {}", id);
    }
}
