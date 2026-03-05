package com.bound.test.client.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CompanyDto {
	
	private Integer id;
	private String name;
	private String description;
	private String location;
}
