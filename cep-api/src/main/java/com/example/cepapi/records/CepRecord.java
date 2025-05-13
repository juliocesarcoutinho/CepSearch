package com.example.cepapi.records;

public record CepRecord(
        String zipCode,
        String street,
        String district,
        String city,
        String state
) {
}
