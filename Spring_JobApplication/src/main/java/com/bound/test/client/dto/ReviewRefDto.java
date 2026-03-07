package com.bound.test.client.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReviewRefDto {

    private Integer id;
    private Integer rating;
    private String comment;
    private Integer jobId;
    private Integer companyId;
}
