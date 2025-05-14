package com.example.cepapi.records;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

@Schema(name = "Zip Code", description = "Zip Code Record")
public record CepRecord(
        @NotBlank(message = "The zip code is mandatory") String zipCode,
        String street,
        String district,
        String city,
        String state
) {
}
