package com.example.jobsearchboardtalika.controllers;

import com.example.jobsearchboardtalika.dto.company.CompanyCreationDTO;
import com.example.jobsearchboardtalika.dto.company.CompanyDTO;
import com.example.jobsearchboardtalika.models.Company;
import com.example.jobsearchboardtalika.repositories.CompanyRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

public class CompanyControllerCreationTest extends BaseControllerTest {

    static final String BASE_URL = "/api/companies";

    @Autowired
    private CompanyRepository repository;

    @Test
    void create() throws Exception {
        final var companyName = "Test Company - 1";
        final var company1 = new CompanyCreationDTO();
        company1.setName(companyName);

        final var result = mock.perform(
                        post(BASE_URL).contentType(APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(company1)))
                .andExpect(status().isCreated())
                .andExpect(content().contentTypeCompatibleWith(APPLICATION_JSON))
                .andExpect(jsonPath("$.name", is(companyName)))
                .andExpect(jsonPath("$.id").isNotEmpty())
                .andDo(print())
                .andReturn();

        final var responseCompany = objectMapper
                .readValue(result.getResponse().getContentAsString(), CompanyDTO.class);
        final var companyInDb = repository.findById(responseCompany.getId());

        assertTrue(companyInDb.isPresent());
        assertEquals(responseCompany.getId(), companyInDb.get().getId());
        assertEquals(responseCompany.getName(), companyInDb.get().getName());
    }

    @Test
    void update() throws Exception {
        final var companyName = "Test Company - created";
        final var company = new Company();
        company.setName(companyName);

        final var companyCreated = repository.save(company);

        final var companyName2 = "Test Company - updated";
        final var company2 = new CompanyCreationDTO();
        company2.setName(companyName2);

        mock.perform(put(BASE_URL + "/{id}", companyCreated.getId())
                        .contentType(APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(company2)))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(APPLICATION_JSON))
                .andExpect(jsonPath("$.name", is(companyName2)))
                .andExpect(jsonPath("$.id").isNotEmpty())
                .andExpect(jsonPath("$.id", is(company.getId()), Long.class))
                .andDo(print());
    }

    @Test
    void destroy() throws Exception {
        final var companyName = "Test Company - deleted";
        final var company = new Company();
        company.setName(companyName);

        final var companySaved = repository.save(company);

        mock.perform(delete(BASE_URL + "/{id}", companySaved.getId()).contentType(APPLICATION_JSON))
                .andExpect(status().isNoContent())
                .andDo(print());

        final var deletedCompany = repository.findById(companySaved.getId());

        assertNotNull(companySaved.getId());
        assertTrue(deletedCompany.isEmpty());
    }
}
