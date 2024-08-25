package com.example.lifters.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ConstituentDto {

    @NotBlank(message = "First name is required")
    private String firstName;

    @NotBlank(message = "Last name is required")
    private String lastName;

    @NotNull(message = "Document number is required")
    @NotBlank(message = "Document number cannot be blank")
    private String documentNumber;

    private Integer votes;
}
