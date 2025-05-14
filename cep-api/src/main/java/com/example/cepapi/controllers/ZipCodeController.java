package com.example.cepapi.controllers;

import com.example.cepapi.controllers.docs.ZipCodeControllerDocs;
import com.example.cepapi.records.ZipCodeRecord;
import com.example.cepapi.services.ZipCodeService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/zips")
@Tag(name = "Zip Code", description = "Zip Code Search Endpoints")
public class ZipCodeController implements ZipCodeControllerDocs {

    private final ZipCodeService zipCodeService;
    public ZipCodeController(ZipCodeService zipCodeService) {
        this.zipCodeService = zipCodeService;
    }

    @GetMapping("/{zipCode}")
    @Override
    public ResponseEntity<ZipCodeRecord> findCep(@PathVariable String zipCode) {
        ZipCodeRecord resposta = zipCodeService.findZipCode(zipCode);
        return ResponseEntity.ok(resposta);
    }
}