package com.bound.test.client.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class JobsRefDto {

    private Integer id;
    private String title;
    private String description;
    private String location;
    private Double salary;
    private Integer companyId;
}