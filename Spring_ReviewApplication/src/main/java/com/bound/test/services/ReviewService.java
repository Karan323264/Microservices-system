package com.bound.test.services;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;
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
	private final RestTemplate restTemplate;
	
	public ReviewDto create(ReviewDto dto) {

        Integer companyId = dto.getCompanyId();
        Integer jobId = dto.getJobId();

        // Company validation
        try {
            ResponseEntity<Object> companyResponse =
                    restTemplate.getForEntity(
                            "http://company-service:8082/company/" + companyId,
                            Object.class
                    );

            if (!companyResponse.getStatusCode().is2xxSuccessful()) {
                throw new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Company not found with id: " + companyId
                );
            }

        } catch (Exception e) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "Company not found with id: " + companyId
            );
        }

        // Job validation
        try {
            ResponseEntity<Object> jobResponse =
                    restTemplate.getForEntity(
                            "http://job-service:8081/jobs/" + jobId,
                            Object.class
                    );

            if (!jobResponse.getStatusCode().is2xxSuccessful()) {
                throw new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Job not found with id: " + jobId
                );
            }

        } catch (Exception e) {
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
