package com.example.jobsearchboardtalika.services.impl;

import com.example.jobsearchboardtalika.dto.company.CompanyCreationDTO;
import com.example.jobsearchboardtalika.dto.company.CompanyDTO;
import com.example.jobsearchboardtalika.mappers.EntityMapper;
import com.example.jobsearchboardtalika.models.Company;
import com.example.jobsearchboardtalika.repositories.CompanyRepository;
import com.example.jobsearchboardtalika.services.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class CompanyServiceImpl implements CompanyService {

    @Autowired
    private CompanyRepository repository;

    @Autowired
    private EntityMapper mapper;

    public List<CompanyDTO> getAll() {
        final List<Company> companies = repository.findAll();
        return mapper.mapAsList(companies, CompanyDTO.class);
    }

    @Override
    public CompanyDTO findById(final Long id) {
        final Company company = findCompanyById(id);
        return mapper.map(company, CompanyDTO.class);
    }

    @Override
    public CompanyDTO create(final CompanyCreationDTO companyInfo) {
        final Company newCompany = mapper.map(companyInfo, Company.class);
        repository.save(newCompany);
        return mapper.map(newCompany, CompanyDTO.class);
    }

    @Override
    public CompanyDTO update(final Long id, final CompanyCreationDTO companyInfo) {
        final Company company = findCompanyById(id);
        company.setName(companyInfo.getName());
        company.setFoundedYear(companyInfo.getFoundedYear());
        company.setNumberOfStaff(companyInfo.getNumberOfStaff());
        company.setType(companyInfo.getType());
        company.setDescription(companyInfo.getDescription());
        company.setUpdatedAt(LocalDateTime.now());
        repository.save(company);
        return mapper.map(company, CompanyDTO.class);
    }

    @Override
    public void delete(final Long id) {
        final Company company = findCompanyById(id);
        repository.delete(company);
    }

    private Company findCompanyById(final Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Company is not found"));
    }
}
