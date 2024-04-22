package com.example.demo.controller;

import com.example.demo.common.contract.Response;
import com.example.demo.contract.BirthRegistrationRequest;
import com.example.demo.contract.BirthRegistrationResponse;
import com.example.demo.model.BirthRegistrationDetails;
import com.example.demo.service.BirthRegistrationService;

import io.swagger.v3.oas.annotations.Operation;

import jakarta.validation.Valid;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/assessment")
@RequiredArgsConstructor
public class BirthRegistrationController {

    private final BirthRegistrationService birthRegistrationService;

    @SneakyThrows
    @Operation(summary = "Save Birth Details request")
    @PostMapping(value = {"/save-birth-details"})
    public ResponseEntity<?> saveBirthDetails(
            @Valid @RequestBody BirthRegistrationRequest request) {
        BirthRegistrationDetails birthRegistrationDetails =
                birthRegistrationService.saveBirthDetails(request);
        Response response = Response.builder().payload(birthRegistrationDetails.getId()).build();
        return new ResponseEntity<>(
                Response.builder().payload(response).message("Successfully saved").build(),
                HttpStatus.OK);
    }

    @GetMapping("/fetch-by-date-of-birth")
    public ResponseEntity<Response<List<BirthRegistrationResponse>>>
            getBirthApplicationBydateofbirth(
                    @RequestParam("fromDate") LocalDate fromDate,
                    @RequestParam("toDate") LocalDate toDate) {
        List<BirthRegistrationResponse> response =
                birthRegistrationService.getBirthApplicationBydateofbirth(fromDate, toDate);
        if (response != null) {
            return new ResponseEntity<>(
                    Response.<List<BirthRegistrationResponse>>builder()
                            .payload(response)
                            .message("Saved Successfully.")
                            .build(),
                    HttpStatus.OK);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/fetch-by-application-number/{registrationNumber}")
    public ResponseEntity<Response<BirthRegistrationResponse>>
            getBirthApplicationByApplicationNumber(
                    @PathVariable("registrationNumber") String registrationNumber) {
        BirthRegistrationResponse response =
                birthRegistrationService.getBirthApplicationByRegistrationNumber(
                        registrationNumber);
        if (response != null) {
            return new ResponseEntity<>(
                    Response.<BirthRegistrationResponse>builder()
                            .payload(response)
                            .message("Saved Successfully.")
                            .build(),
                    HttpStatus.OK);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
