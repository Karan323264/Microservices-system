package com.bound.test.services;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import com.bound.test.client.CompanyClient;
import com.bound.test.client.JobClient;
import com.bound.test.dtos.ReviewDto;
import com.bound.test.entity.Review;
import com.bound.test.mapper.ReviewMapper;
import com.bound.test.repository.ReviewRepo;

import feign.FeignException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ReviewService {
	
	private final ReviewRepo reviewRepo;
	private final ReviewMapper reviewMapper;
	private final JobClient jobClient;
	private final CompanyClient companyClient;
	
	public ReviewDto create(ReviewDto dto) {

	    Integer companyId = dto.getCompanyId();
	    Integer jobId = dto.getJobId();

	    try {
	        companyClient.getCompanyById(companyId);
	    } catch (FeignException.NotFound e) {
	        throw new ResponseStatusException(
	                HttpStatus.NOT_FOUND,
	                "Company not found with id: " + companyId
	        );
	    }

	    try {
	        jobClient.getJobById(jobId);
	    } catch (FeignException.NotFound e) {
	        throw new ResponseStatusException(
	                HttpStatus.NOT_FOUND,
	                "Job not found with id: " + jobId
	        );
	    }

	    Review review = reviewMapper.toEntity(dto);
	    return reviewMapper.toDto(reviewRepo.save(review));
	}

	public List<ReviewDto> getAllReviews() {
		return reviewRepo.findAll()
				.stream()
				.map(reviewMapper::toDto)
				.toList();
	}

	public List<ReviewDto> getReviewByCompany(Integer companyId) {
		return reviewRepo.findByCompanyId(companyId)
				.stream()
				.map(reviewMapper::toDto)
				.toList();
	}

	public List<ReviewDto> getReviewByJob(Integer jobId) {
		return reviewRepo.findByJobId(jobId)
				.stream()
				.map(reviewMapper::toDto)
				.toList();
	}

	public void deleteReview(Integer id) {
		reviewRepo.deleteById(id);
	}
	
	
}
