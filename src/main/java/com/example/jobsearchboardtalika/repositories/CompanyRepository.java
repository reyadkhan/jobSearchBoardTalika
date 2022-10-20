package com.example.jobsearchboardtalika.repositories;

import com.example.jobsearchboardtalika.models.Company;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyRepository extends JpaRepository<Company, Long> {
}
