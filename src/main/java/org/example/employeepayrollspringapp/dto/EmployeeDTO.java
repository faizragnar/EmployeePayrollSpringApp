package org.example.employeepayrollspringapp.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeDTO {
    private int id;
    private String name;
    private String department;
    private double salary;

}
