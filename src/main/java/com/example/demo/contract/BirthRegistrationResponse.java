package com.example.demo.contract;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.*;

import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BirthRegistrationResponse {

    private UUID id;
    private String motherName;
    private LocalDate dateOfBirth;
    private String registrationNumber;
    private LocalDate registrationDate;
}
