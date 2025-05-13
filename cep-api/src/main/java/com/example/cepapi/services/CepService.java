package com.example.cepapi.services;

import com.example.cepapi.entities.HistoryLog;
import com.example.cepapi.exception.ResourceNotFoundException;
import com.example.cepapi.records.CepRecord;
import com.example.cepapi.repositories.HistoryLogRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
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
        CepRecord response = buscarCepNoWiremock(cep);
        String responseJson = converterParaJson(response);
        salvarLogConsulta(cep, responseJson);
        return response;
    }

    private CepRecord buscarCepNoWiremock(String cep) {
        String url = "http://localhost:8081/cep/" + cep;
        try {
            CepRecord response = restTemplate.getForObject(url, CepRecord.class);
            if (response == null) {
                throw new ResourceNotFoundException("Zip not found: " + cep);
            }
            return response;
        } catch (HttpClientErrorException.NotFound ex) {
            throw new ResourceNotFoundException("Zip not found: " + cep);
        } catch (Exception ex) {
            throw new RuntimeException("Error when consulting the zip service", ex);
        }
    }

    private String converterParaJson(CepRecord response) {
        try {
            return objectMapper.writeValueAsString(response);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Erro ao converter resposta para JSON", e);
        }
    }

    private void salvarLogConsulta(String cep, String responseJson) {
        var log = HistoryLog.builder()
                .cep(cep)
                .infoZipCode(responseJson)
                .dateTime(LocalDateTime.now())
                .build();
        repository.save(log);
    }
}