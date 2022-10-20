package com.example.jobsearchboardtalika.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
public class BaseControllerTest {

    @Autowired
    MockMvc mock;

    @Autowired
    ObjectMapper objectMapper;

    static final String BASE_URL = "/api/companies";
}
