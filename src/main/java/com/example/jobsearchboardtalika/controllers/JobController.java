package com.example.jobsearchboardtalika.controllers;

import com.example.jobsearchboardtalika.annotations.RestPrefixController;
import com.example.jobsearchboardtalika.dto.JobDTO;
import com.example.jobsearchboardtalika.services.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import static org.springframework.data.domain.Sort.Direction.*;

@RestPrefixController
public class JobController {

    @Autowired
    private JobService service;

    @GetMapping("jobs")
    public ResponseEntity<Page<JobDTO>> index(
            @RequestParam(defaultValue = "1", required = false) final Integer page,
            @RequestParam(defaultValue = "15", required = false) final Integer pageSize) {
        final PageRequest pageRequest = PageRequest.of(page, pageSize, Sort.by(DESC, "updatedAt"));
        return ResponseEntity.ok(service.getPage(pageRequest));
    }
}
