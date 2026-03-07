package com.bound.test.client;

import com.bound.test.client.dto.JobsRefDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "job-service", url = "http://job-service:8081")
public interface JobClient {

    @GetMapping("/jobs/company/{companyId}")
    List<JobsRefDto> getJobsByCompanyId(@PathVariable Integer companyId);
}
