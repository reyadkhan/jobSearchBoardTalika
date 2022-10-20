package com.example.jobsearchboardtalika.dto.company;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class CompanyCreationDTO {

    @NotBlank(message = "Company name is mandatory")
    private String name;

    private String numberOfStaff;

    private String type;

    private Integer foundedYear;

    private String description;
}
