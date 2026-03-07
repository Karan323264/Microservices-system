package com.bound.test.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import com.bound.test.dtos.CompanyDto;
import com.bound.test.dtos.CompanyWithJobsDto;
import com.bound.test.dtos.CompanyWithReviewsDto;
import com.bound.test.services.CompanyService;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/company")
public class CompanyController {
	
	private final CompanyService companyService;
	
	@PostMapping("/create")
	@ResponseStatus(HttpStatus.CREATED)
	CompanyDto createCompany(@RequestBody CompanyDto requestCompany) {
		return companyService.createCompany(requestCompany);
	}
	
	@GetMapping
	List<CompanyDto> getAll() {
		return companyService.getAll();
	}
	
	@GetMapping("/{id}")
	public CompanyDto getCompanyById(@PathVariable Integer id){
		return companyService.getCompanyById(id);
	}
	
	@PutMapping("/update/{id}")
	public CompanyDto update(
			@PathVariable Integer id, 
			@RequestBody CompanyDto requestCompanyUpdate) {
		return companyService.update(id, requestCompanyUpdate);
	}
	
	@DeleteMapping("/delete/{id}")
	public void deleteCompany(@PathVariable Integer id) {
		companyService.deleteCompany(id);
	}
	
	@GetMapping("/{id}/jobs")
	public ResponseEntity<CompanyWithJobsDto> getCompanyWithJobs(
			@PathVariable Integer id) {
		return ResponseEntity.ok(
				companyService.getCompanyWithJobs(id));
	}
	
	@GetMapping("/{companyId}/reviews")
	public CompanyWithReviewsDto getCompanyWithReviews(@PathVariable Integer companyId) {
		return companyService.getCompanyWithReviews(companyId);
	}
}
