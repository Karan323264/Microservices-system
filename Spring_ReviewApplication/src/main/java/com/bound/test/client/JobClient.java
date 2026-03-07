package com.bound.test.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import com.bound.test.client.dto.JobRefDto;

@FeignClient(name = "job-service", url = "http://job-service:8081")
public interface JobClient {
	
	@GetMapping("/jobs/{id}")
	JobRefDto getJobById(@PathVariable Integer id);
	
}
