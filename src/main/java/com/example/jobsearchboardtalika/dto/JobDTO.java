package com.example.jobsearchboardtalika.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class JobDTO {

    private Long id;

    private String title;

    private List<String> skills = new ArrayList<>();

    private Integer minSalary;

    private Integer maxSalary;

    private String description;

    private String url;

    private LocalDate expiredOn;
}
