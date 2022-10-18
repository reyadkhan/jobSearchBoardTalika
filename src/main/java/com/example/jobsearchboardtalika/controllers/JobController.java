package com.example.jobsearchboardtalika.controllers;

import com.example.jobsearchboardtalika.annotations.RestPrefixController;
import com.example.jobsearchboardtalika.dto.JobDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

@RestPrefixController
public class JobController {

    @GetMapping("jobs")
    public ResponseEntity<JobDTO> index() {
        final var job = new JobDTO();
        job.setId(1L);
        job.setTitle("This is a test job");
        job.setDescription("This is a static test job description");
        return ResponseEntity.ok(job);
    }
}
