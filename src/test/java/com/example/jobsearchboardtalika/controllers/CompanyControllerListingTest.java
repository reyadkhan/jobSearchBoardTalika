package com.example.jobsearchboardtalika.controllers;

import com.example.jobsearchboardtalika.models.Company;
import com.example.jobsearchboardtalika.repositories.CompanyRepository;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class CompanyControllerListingTest extends BaseControllerTest {

    @MockBean
    private CompanyRepository repository;

    @Test
    void index() throws Exception {
        final var companyName = "Test Company - 1";
        final var company1 = new Company();
        company1.setName(companyName);
        company1.setId(1L);

        when(repository.findAll()).thenReturn(List.of(company1));

        mock.perform(get(BASE_URL).contentType(APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].name", is(companyName)))
                .andDo(print());
    }

    @Test
    void show() throws Exception {
        final var companyName = "Test Company - 1";
        final var company1 = new Company();
        company1.setName(companyName);
        company1.setId(1L);

        when(repository.findById(1L)).thenReturn(Optional.of(company1));
        when(repository.findById(2L)).thenReturn(Optional.empty());

        mock.perform(get(BASE_URL + "/{id}", 1L).contentType(APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(APPLICATION_JSON))
                .andExpect(jsonPath("$.name", is(companyName)))
                .andDo(print());

        mock.perform(get(BASE_URL + "/{id}", 2L).contentType(APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andDo(print());
    }
}