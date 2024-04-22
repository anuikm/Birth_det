package com.example.demo.common.contract;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Response<BirthRegistrationDetails> {
    private BirthRegistrationDetails payload;
    private String message;
}
