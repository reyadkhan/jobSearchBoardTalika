package com.example.jobsearchboardtalika.services;

import com.example.jobsearchboardtalika.dto.company.CompanyCreationDTO;
import com.example.jobsearchboardtalika.dto.company.CompanyDTO;

import java.util.List;

public interface CompanyService {

    List<CompanyDTO> getAll();

    CompanyDTO findById(Long id);

    CompanyDTO create(CompanyCreationDTO companyInfo);

    CompanyDTO update(Long id, CompanyCreationDTO companyInfo);

    void delete(Long id);
}
