package org.example.employeepayrollspringapp.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeDTO{
    private String name;
    private String department;
    private double salary;
}
