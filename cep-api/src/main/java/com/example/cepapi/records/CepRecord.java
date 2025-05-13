package com.example.cepapi.records;

public record CepRecord(
        String zipCode,
        String street,
        String neighborhood,
        String city,
        String state
) {
}
