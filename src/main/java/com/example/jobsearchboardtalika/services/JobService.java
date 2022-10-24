package com.example.jobsearchboardtalika.services;

import com.example.jobsearchboardtalika.dto.JobDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface JobService {

    Page<JobDTO> getPage(Pageable pageable);
}
