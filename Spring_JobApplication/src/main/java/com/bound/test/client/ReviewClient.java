package com.bound.test.client;

import com.bound.test.client.dto.ReviewRefDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "review-service", url = "http://review-service:8083")
public interface ReviewClient {

    @GetMapping("/review/job/{jobId}")
    List<ReviewRefDto> getReviewsByJobId(@PathVariable Integer jobId);

}