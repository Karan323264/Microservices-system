package com.bound.test.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.bound.test.dtos.ReviewDto;
import com.bound.test.entity.Review;
import com.bound.test.mapper.ReviewMapper;
import com.bound.test.repository.ReviewRepo;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ReviewService {
	
	private final ReviewRepo reviewRepo;
	private final ReviewMapper reviewMapper;
	
	public ReviewDto create(ReviewDto requestReview) {
		Review entity = reviewMapper.toEntity(requestReview);
		return reviewMapper.toDto(reviewRepo.save(entity));
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
