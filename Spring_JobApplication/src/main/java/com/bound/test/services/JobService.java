package com.bound.test.services;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;
import com.bound.test.client.CompanyClient;
import com.bound.test.client.ReviewClient;
import com.bound.test.client.dto.ReviewRefDto;
import com.bound.test.dtos.JobDto;
import com.bound.test.dtos.JobWithReviewsDto;
import com.bound.test.entity.Job;
import com.bound.test.mapper.JobMapper;
import com.bound.test.repository.JobRepo;

@Service
@RequiredArgsConstructor
public class JobService {

    private final JobRepo repository;
    private final JobMapper mapper;
    private final CompanyClient companyClient;
    private final ReviewClient reviewClient;

    public JobDto create(JobDto dto) {

       Integer companyId = dto.getCompanyId();
       
       try {
		companyClient.getCompanyById(companyId);
	} catch (Exception e) {
		throw new ResponseStatusException(
	            HttpStatus.NOT_FOUND,
	            "Company not found with id: " + companyId
	        );
	}
       Job entity = mapper.toEntity(dto);
       return mapper.toDto(repository.save(entity));
    }

    public List<JobDto> getAll() {
        return repository.findAll()
                .stream()
                .map(mapper::toDto)
                .toList();
    }

    public JobDto getById(Integer id) {
        Job job = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Job not found"));
        return mapper.toDto(job);
    }

    public JobDto update(Integer id, JobDto dto) {
        Job job = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Job not found"));

        mapper.updateEntity(dto, job);
        return mapper.toDto(repository.save(job));
    }

    public void delete(Integer id) {
        repository.deleteById(id);
    }

	public List<JobDto> getJobsByCompanyId(Integer companyId) {
		
		List<Job> jobs = repository.findByCompanyId(companyId);
		return jobs.stream()
				.map(mapper::toDto)
				.toList();
	}

	public JobWithReviewsDto getJobWithReviews(Integer jobId) {

	    Job job = repository.findById(jobId)
	            .orElseThrow(() -> new RuntimeException("Job not found"));

	    List<ReviewRefDto> reviews = reviewClient.getReviewsByJobId(jobId);

	    JobDto jobDto = mapper.toDto(job);

	    return JobWithReviewsDto.builder()
	            .id(jobDto.getId())
	            .title(jobDto.getTitle())
	            .description(jobDto.getDescription())
	            .location(jobDto.getLocation())
	            .salary(jobDto.getSalary())
	            .companyId(jobDto.getCompanyId())
	            .reviews(reviews)
	            .build();
	}
}