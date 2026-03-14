package com.bound.test.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.bound.test.client.dto.CompanyRefDto;

@FeignClient(name = "company-service")
public interface CompanyClient {

    @GetMapping("/company/{id}")
    CompanyRefDto getCompanyById(@PathVariable Integer id);

}