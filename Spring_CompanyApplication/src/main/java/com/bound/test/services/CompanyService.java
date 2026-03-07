package com.bound.test.services;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import com.bound.test.client.JobClient;
import com.bound.test.client.ReviewClient;
import com.bound.test.client.dto.JobsRefDto;
import com.bound.test.client.dto.ReviewRefDto;
import com.bound.test.dtos.CompanyDto;
import com.bound.test.dtos.CompanyWithJobsDto;
import com.bound.test.dtos.CompanyWithReviewsDto;
import com.bound.test.entity.Company;
import com.bound.test.mapper.CompanyMapper;
import com.bound.test.repository.CompanyRepo;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CompanyService {
	
	private final CompanyRepo companyRepo;
	private final CompanyMapper companyMapper;
	private final JobClient jobClient;
	private final ReviewClient reviewClient;
	
	public CompanyDto createCompany(CompanyDto requestCompany) {
		Company entity = companyMapper.toEntity(requestCompany);
		return companyMapper.toDto(companyRepo.save(entity));
		
	}

	public List<CompanyDto> getAll() {
		return companyRepo.findAll()
				.stream()
				.map(companyMapper::toDto)
				.toList();
	}

	public CompanyDto getCompanyById(Integer id) {
		Company byId = companyRepo.findById(id)
				.orElseThrow(()-> 
				new ResponseStatusException(HttpStatus.NOT_FOUND,
						"Company by Id: " + id + "was not found")
				);
		return companyMapper.toDto(byId);
	}

	public CompanyDto update(Integer id, CompanyDto requestCompanyUpdate) {
		Company existById = companyRepo.findById(id)
				.orElseThrow(()-> 
				new ResponseStatusException(HttpStatus.NOT_FOUND,
						"Company by Id: " + id + "was not found")
				);
		companyMapper.toUpdate(requestCompanyUpdate, existById);
		return companyMapper.toDto(companyRepo.save(existById));
	}

	public void deleteCompany(Integer id) {
		Company existById = companyRepo.findById(id)
				.orElseThrow(()-> 
				new ResponseStatusException(HttpStatus.NOT_FOUND,
						"Company by Id: " + id + "was not found")
				);
		companyRepo.delete(existById);
	}

	public CompanyWithJobsDto getCompanyWithJobs(Integer companyId) {

	    Company company = companyRepo.findById(companyId)
	            .orElseThrow(() -> new RuntimeException("Company not found"));

	    List<JobsRefDto> jobs = jobClient.getJobsByCompanyId(companyId);

	    return CompanyWithJobsDto.builder()
	            .id(company.getId())
	            .name(company.getName())
	            .description(company.getDescription())
	            .location(company.getLocation())
	            .jobs(jobs)
	            .build();
	}

	public CompanyWithReviewsDto getCompanyWithReviews(Integer companyId) {
		
		Company company = companyRepo.findById(companyId)
				.orElseThrow(()-> new ResponseStatusException(
						HttpStatus.NOT_FOUND,
						"company by Id: " + companyId + "was not found")
						);
		List<ReviewRefDto> reviews = reviewClient.getReviewsByCompanyId(companyId);
		
		CompanyDto dto = companyMapper.toDto(company);
		return CompanyWithReviewsDto.builder()
				.id(dto.getId())
				.name(dto.getName())
				.description(dto.getDescription())
				.location(dto.getLocation())
				.reviews(reviews)
				.build();
	}
}
