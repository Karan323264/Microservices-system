package com.bound.test.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import com.bound.test.dtos.ReviewDto;
import com.bound.test.services.ReviewService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/review")
public class ReviewController {
	
	private final ReviewService reviewService;
	
	@PostMapping("/create")
	@ResponseStatus(HttpStatus.CREATED)
	public ReviewDto create(@RequestBody ReviewDto requestReview) {
		return reviewService.create(requestReview);
	}
	
	@GetMapping
	List<ReviewDto> getAllReviews() {
		return reviewService.getAllReviews();
	}
	
	@GetMapping("/company/{companyId}")
	public List<ReviewDto> getReviewByCompany (@PathVariable Integer companyId) {
		return reviewService.getReviewByCompany(companyId);
	}
	
	@GetMapping("/job/{jobId}")
	public List<ReviewDto> getReviewByJob(@PathVariable Integer jobId) {
		return reviewService.getReviewByJob(jobId);
	}
	
	@DeleteMapping("/delete/{id}")
	public void deleteReview(@PathVariable Integer id) {
		reviewService.deleteReview(id);
	}
}
