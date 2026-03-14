package com.bound.test.client;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.bound.test.client.dto.ReviewRefDto;

@FeignClient(name = "review-service")
public interface ReviewClient {
	
	@GetMapping("/review/company/{companyId}")
	List <ReviewRefDto> getReviewsByCompanyId(@PathVariable Integer companyId);
}
