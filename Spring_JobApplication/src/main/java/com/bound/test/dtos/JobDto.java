package com.bound.test.dtos;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class JobDto {

    private Integer id;
    private String title;
    private String description;
    private String location;
    private Double salary;
    private Integer companyId;
}
