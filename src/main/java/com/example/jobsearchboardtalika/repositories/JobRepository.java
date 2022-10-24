package com.example.jobsearchboardtalika.repositories;

import com.example.jobsearchboardtalika.models.Job;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JobRepository extends JpaRepository<Job, Long> {
}
