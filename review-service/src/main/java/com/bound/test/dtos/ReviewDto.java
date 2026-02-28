package com.bound.test.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReviewDto {

    private Integer id;        
    private Integer rating;
    private String comment;
    private Integer jobId;
    private Integer companyId;
}
