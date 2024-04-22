package com.example.demo.model;

import jakarta.persistence.*;

import lombok.*;

import java.time.LocalDate;
import java.util.UUID;

@AllArgsConstructor
@Builder
@Setter
@Getter
@EqualsAndHashCode
@NoArgsConstructor
@Entity
@Table(name = "birth_registration")
public class BirthRegistrationDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String motherName;
    private LocalDate dateOfBirth;
    private String registrationNumber;
    private LocalDate registrationDate;
}
