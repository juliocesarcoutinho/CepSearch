package com.example.cepapi.services;

import com.example.cepapi.entities.HistoryLog;
import com.example.cepapi.exception.ResourceNotFoundException;
import com.example.cepapi.records.CepRecord;
import com.example.cepapi.repositories.HistoryLogRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;

@Service
public class CepService {

    private final RestTemplate restTemplate;
    private final HistoryLogRepository repository;
    private final ObjectMapper objectMapper;

    public CepService(RestTemplate restTemplate, HistoryLogRepository repository, ObjectMapper objectMapper) {
        this.restTemplate = restTemplate;
        this.repository = repository;
        this.objectMapper = objectMapper;
    }

    public CepRecord consultarCep(String cep) {
        String url = "http://localhost:8081/cep/" + cep;
        try {
            CepRecord response = restTemplate.getForObject(url, CepRecord.class);
            if (response == null) {
                throw new ResourceNotFoundException("Zip not found: " + cep);
            }

            String responseJson;
            try {
                responseJson = objectMapper.writeValueAsString(response);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
                throw new RuntimeException("Erro ao converter resposta para JSON", e);
            }

            var log = HistoryLog.builder()
                    .cep(cep)
                    .infoZipCode(responseJson)
                    .dateTime(LocalDateTime.now())
                    .build();

            repository.save(log);

            return response;
        } catch (org.springframework.web.client.HttpClientErrorException.NotFound ex) {
            throw new ResourceNotFoundException("Zip not found: " + cep);
        } catch (Exception ex) {
            throw new RuntimeException("Error when consulting the zip service", ex);
        }
    }
}