package com.example.jobsearchboardtalika.controllers;

import com.example.jobsearchboardtalika.models.Job;
import com.example.jobsearchboardtalika.repositories.JobRepository;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;

import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.data.domain.Sort.Direction.DESC;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class JobControllerListingTest extends BaseControllerTest {

    static final String BASE_URL = "/api/jobs";

    @MockBean
    private JobRepository repository;

    @Test
    void index() throws Exception {
        final Job job = new Job();
        job.setId(1L);

        final Pageable pageable = PageRequest.of(1, 20, Sort.by(DESC, "updatedAt"));
        final Page<Job> jobs = new PageImpl<>(List.of(job), pageable, 1);

        when(repository.findAll(pageable)).thenReturn(jobs);

        mock.perform(get(BASE_URL).contentType(APPLICATION_JSON)
                        .param("page", "1")
                        .param("pageSize", "20"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(APPLICATION_JSON))
                .andExpect(jsonPath("$.content", hasSize(1)))
                .andExpect(jsonPath("$.pageable").exists());

        final ArgumentCaptor<Pageable> pageableArgumentCaptor = ArgumentCaptor.forClass(Pageable.class);
        verify(repository).findAll(pageableArgumentCaptor.capture());
        final PageRequest pageRequest = (PageRequest) pageableArgumentCaptor.getValue();

        assertEquals(1, pageRequest.getPageNumber());
        assertEquals(20, pageRequest.getPageSize());

        final Order actualOrder = pageable.getSort().getOrderFor("updatedAt");

        assertNotNull(actualOrder);
        assertEquals(DESC, actualOrder.getDirection());
    }
}