package com.bound.test.dtos;

import java.util.List;

import com.bound.test.client.dto.ReviewRefDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CompanyWithReviewsDto {
	
	private Integer id;
	private String name;
	private String description;
	private String location;
	
	private List<ReviewRefDto> reviews;
}
