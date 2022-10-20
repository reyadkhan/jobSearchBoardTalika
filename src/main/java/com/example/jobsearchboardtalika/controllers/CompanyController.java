package com.example.jobsearchboardtalika.controllers;

import com.example.jobsearchboardtalika.annotations.RestPrefixController;
import com.example.jobsearchboardtalika.dto.company.CompanyCreationDTO;
import com.example.jobsearchboardtalika.dto.company.CompanyDTO;
import com.example.jobsearchboardtalika.services.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.net.URI;
import java.util.List;

@RestPrefixController
public class CompanyController {

    @Autowired
    private CompanyService service;

    @GetMapping("companies")
    public ResponseEntity<List<CompanyDTO>> index() {
        return ResponseEntity.ok(service.getAll());
    }

    @GetMapping("companies/{id}")
    public ResponseEntity<CompanyDTO> show(@PathVariable final Long id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @PostMapping("companies")
    public ResponseEntity<CompanyDTO> create(@Validated @RequestBody final CompanyCreationDTO companyInfo) {
        final CompanyDTO company = service.create(companyInfo);
        return ResponseEntity.created(URI.create("/companies/" + company.getId())).body(company);
    }

    @PutMapping("companies/{id}")
    public ResponseEntity<CompanyDTO> update(@Validated @RequestBody final CompanyCreationDTO companyInfo, @PathVariable final Long id) {
        return ResponseEntity.ok(service.update(id, companyInfo));
    }

    @DeleteMapping("companies/{id}")
    public ResponseEntity<Void> destroy(@PathVariable final Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
