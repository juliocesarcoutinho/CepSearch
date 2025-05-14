package com.example.cepapi.controllers;

import com.example.cepapi.controllers.docs.CepControllerDocs;
import com.example.cepapi.records.CepRecord;
import com.example.cepapi.services.CepService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/zips")
@Tag(name = "Zip Code", description = "Zip Code Search Endpoints")
public class CepController implements CepControllerDocs {

    private final CepService cepService;

    public CepController(CepService cepService) {
        this.cepService = cepService;
    }

    @GetMapping("/{cep}")
    @Override
    public ResponseEntity<CepRecord> findCep(@PathVariable String cep) {
        CepRecord resposta = cepService.consultarCep(cep);
        return ResponseEntity.ok(resposta);
    }
}