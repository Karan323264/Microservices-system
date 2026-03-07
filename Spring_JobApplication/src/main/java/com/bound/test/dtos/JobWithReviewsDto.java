package com.bound.test.dtos;

import com.bound.test.client.dto.ReviewRefDto;
import lombok.*;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class JobWithReviewsDto {

    private Integer id;
    private String title;
    private String description;
    private String location;
    private Double salary;
    private Integer companyId;

    private List<ReviewRefDto> reviews;
}