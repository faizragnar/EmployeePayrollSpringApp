package org.example.employeepayrollspringapp.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeDTO {
    private int id;
    @NotBlank(message = "Name is required")
    @Size(max = 10, message = "Name must not exceed 10 characters")
    @Pattern(regexp = "^[A-Z][a-zA-Z\\s]*$", message = "First letter should be Capital and Name can only contain letters")
    private String name;
    private String department;
    private double salary;

}
