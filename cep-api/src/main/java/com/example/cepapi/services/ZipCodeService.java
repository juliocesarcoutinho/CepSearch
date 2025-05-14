package com.example.cepapi.services;

import com.example.cepapi.entities.HistoryLog;
import com.example.cepapi.exception.ResourceNotFoundException;
import com.example.cepapi.records.ZipCodeRecord;
import com.example.cepapi.repositories.HistoryLogRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;

@Service
public class ZipCodeService {

    private final RestTemplate restTemplate;
    private final HistoryLogRepository repository;
    private final ObjectMapper objectMapper;

    public ZipCodeService(RestTemplate restTemplate, HistoryLogRepository repository, ObjectMapper objectMapper) {
        this.restTemplate = restTemplate;
        this.repository = repository;
        this.objectMapper = objectMapper;
    }

    public ZipCodeRecord findZipCode(String zipCode) {
        ZipCodeRecord response = findZipCodeWiremocks(zipCode);
        String responseJson = convertToJson(response);
        saveLogQuery(zipCode, responseJson);
        return response;
    }

    private ZipCodeRecord findZipCodeWiremocks(String zipcode) {
        String url = "http://localhost:8081/cep/" + zipcode;
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

    private String convertToJson(ZipCodeRecord response) {
        try {
            return objectMapper.writeValueAsString(response);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Erro ao converter resposta para JSON", e);
        }
    }

    private void saveLogQuery(String zipCode, String responseJson) {
        var log = HistoryLog.builder()
                .zipCode(zipCode)
                .infoZipCode(responseJson)
                .dateTime(LocalDateTime.now())
                .build();
        repository.save(log);
    }
}