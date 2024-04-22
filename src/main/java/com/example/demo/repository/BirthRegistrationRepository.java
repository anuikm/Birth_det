package com.example.demo.repository;

import com.example.demo.model.BirthRegistrationDetails;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface BirthRegistrationRepository extends JpaRepository<BirthRegistrationDetails, UUID> {

    List<BirthRegistrationDetails> findByDateOfBirthBetween(LocalDate fromDate, LocalDate toDate);

    Optional<BirthRegistrationDetails> findByRegistrationNumber(String registrationNumber);
}
