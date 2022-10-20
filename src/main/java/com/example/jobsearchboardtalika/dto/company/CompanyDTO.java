package com.example.jobsearchboardtalika.dto.company;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class CompanyDTO {

    private Long id;

    private String name;

    private String numberOfStaff;

    private String type;

    private Integer foundedYear;

    private String description;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}
