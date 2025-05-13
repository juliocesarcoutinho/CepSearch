package com.example.cepapi.controllers;

import com.example.cepapi.records.CepRecord;
import com.example.cepapi.services.CepService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/ceps")
public class CepController {

    private final CepService cepService;

    public CepController(CepService cepService) {
        this.cepService = cepService;
    }

    @GetMapping("/{cep}")
    public ResponseEntity<CepRecord> buscarCep(@PathVariable String cep) {
        CepRecord resposta = cepService.consultarCep(cep);
        return ResponseEntity.ok(resposta);
    }
}