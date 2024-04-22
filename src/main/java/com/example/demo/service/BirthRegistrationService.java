package com.example.demo.service;

import static org.modelmapper.config.Configuration.AccessLevel.PRIVATE;

import com.example.demo.contract.BirthRegistrationRequest;
import com.example.demo.contract.BirthRegistrationResponse;
import com.example.demo.model.BirthRegistrationDetails;
import com.example.demo.repository.BirthRegistrationRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.modelmapper.Conditions;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class BirthRegistrationService {
    private final BirthRegistrationRepository repository;

    private final ModelMapper modelMapper = new ModelMapper();

    public BirthRegistrationDetails saveBirthDetails(BirthRegistrationRequest request) {
        BirthRegistrationDetails birthRegistrationDetails =
                modelMapper.map(request, BirthRegistrationDetails.class);
        return repository.save(birthRegistrationDetails);
    }

    public List<BirthRegistrationResponse> getBirthApplicationBydateofbirth(
            LocalDate fromDate, LocalDate toDate) {
        List<BirthRegistrationDetails> birthRegistrationDetails =
                repository.findByDateOfBirthBetween(fromDate, toDate);
        List<BirthRegistrationResponse> responseList = null;
        responseList =
                birthRegistrationDetails.stream()
                        .map(
                                registerBook ->
                                        modelMapper.map(
                                                registerBook, BirthRegistrationResponse.class))
                        .collect(Collectors.toList());
        return responseList;
    }

    public BirthRegistrationResponse getBirthApplicationByRegistrationNumber(
            String registrationNumber) {
        BirthRegistrationDetails birthApplication =
                repository
                        .findByRegistrationNumber(registrationNumber)
                        .orElseThrow(
                                () ->
                                        new IllegalArgumentException(
                                                "Application number not found with number: "
                                                        + registrationNumber));
        if (birthApplication != null) {
            modelMapper
                    .getConfiguration()
                    .setFieldMatchingEnabled(true)
                    .setFieldAccessLevel(PRIVATE)
                    //                    .setMatchingStrategy(MatchingStrategies.STRICT)
                    .setPropertyCondition(Conditions.isNotNull());

            return mapToResponse(birthApplication);
        }
        return null;
    }

    private BirthRegistrationResponse mapToResponse(BirthRegistrationDetails birthApplication) {
        BirthRegistrationResponse response =
                modelMapper.map(birthApplication, BirthRegistrationResponse.class);
        return response;
    }
}
