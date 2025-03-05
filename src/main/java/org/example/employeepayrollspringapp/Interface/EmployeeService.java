package org.example.employeepayrollspringapp.Interface;

import org.example.employeepayrollspringapp.model.Employee;
import java.util.List;

public interface EmployeeService {
    List<Employee> getAllEmployees();
    Employee getEmployeeById(int id);
    Employee addEmployee(Employee employee);
    Employee updateEmployee(int id, Employee updatedEmployee);
    void deleteEmployee(int id);
}
