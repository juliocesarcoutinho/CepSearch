package com.example.cepapi.services;

import com.example.cepapi.entities.HistoryLog;
import com.example.cepapi.exception.ResourceNotFoundException;
import com.example.cepapi.records.ZipCodeRecord;
import com.example.cepapi.repositories.HistoryLogRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;

@Service
public class ZipCodeService {

    private final RestTemplate restTemplate;
    private final HistoryLogRepository repository;
    private final ObjectMapper objectMapper;

    @Value("${zip.api.url}")
    private String urlBase;

    public ZipCodeService(RestTemplate restTemplate, HistoryLogRepository repository, ObjectMapper objectMapper) {
        this.restTemplate = restTemplate;
        this.repository = repository;
        this.objectMapper = objectMapper;
    }

    public ZipCodeRecord findZipCode(String zipCode) {
        ZipCodeRecord response = findZipCodeWiremocks(zipCode);
        saveLogQuery(zipCode, response);
        return response;
    }

    private ZipCodeRecord findZipCodeWiremocks(String zipcode) {
        String url = urlBase + zipcode;
        try {
            ZipCodeRecord response = restTemplate.getForObject(url, ZipCodeRecord.class);
            if (response == null) {
                throw new ResourceNotFoundException("Zip not found: " + zipcode);
            }
            return response;
        } catch (HttpClientErrorException.NotFound ex) {
            throw new ResourceNotFoundException("Zip not found: " + zipcode);
        } catch (Exception ex) {
            throw new RuntimeException("Error when consulting the zip service", ex);
        }
    }

    private void saveLogQuery(String zipCode, ZipCodeRecord response) {
        var log = HistoryLog.builder()
                .zipCode(zipCode)
                .street(response.street())
                .city(response.city())
                .state(response.state())
                .dateTime(LocalDateTime.now())
                .build();
        repository.save(log);
    }
}