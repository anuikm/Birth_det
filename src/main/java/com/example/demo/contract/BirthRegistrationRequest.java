package com.example.demo.contract;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class BirthRegistrationRequest {

    @NotBlank(message = "mother name cannot be empty")
    private String motherName;

    @NotNull(message = "address cannot be empty")
    private LocalDate dateOfBirth;

    @NotNull(message = " registration number cannot be empty")
    private String registrationNumber;

    @NotNull(message = "headid cannot be empty")
    private LocalDate registrationDate;
}
