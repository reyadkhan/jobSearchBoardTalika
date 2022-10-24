package com.example.jobsearchboardtalika.services.impl;

import com.example.jobsearchboardtalika.dto.JobDTO;
import com.example.jobsearchboardtalika.mappers.EntityMapper;
import com.example.jobsearchboardtalika.models.Job;
import com.example.jobsearchboardtalika.repositories.JobRepository;
import com.example.jobsearchboardtalika.services.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class JobServiceImpl implements JobService {

    @Autowired
    private JobRepository repository;

    @Autowired
    private EntityMapper mapper;

    @Override
    public Page<JobDTO> getPage(final Pageable pageable) {
        final Page<Job> jobs = repository.findAll(pageable);
        return jobs.map(job -> mapper.map(job, JobDTO.class));
    }
}
