package com.bound.test.services;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import com.bound.test.dtos.CompanyDto;
import com.bound.test.entity.Company;
import com.bound.test.mapper.CompanyMapper;
import com.bound.test.repository.CompanyRepo;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CompanyService {
	
	private final CompanyRepo companyRepo;
	private final CompanyMapper companyMapper;
	
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
				new ResponseStatusException(HttpStatus.BAD_REQUEST,
						"Company by Id: " + id + "was not found")
				);
		return companyMapper.toDto(byId);
	}

	public CompanyDto update(Integer id, CompanyDto requestCompanyUpdate) {
		Company existById = companyRepo.findById(id)
				.orElseThrow(()-> 
				new ResponseStatusException(HttpStatus.BAD_REQUEST,
						"Company by Id: " + id + "was not found")
				);
		companyMapper.toUpdate(requestCompanyUpdate, existById);
		return companyMapper.toDto(companyRepo.save(existById));
	}

	public void deleteCompany(Integer id) {
		Company existById = companyRepo.findById(id)
				.orElseThrow(()-> 
				new ResponseStatusException(HttpStatus.BAD_REQUEST,
						"Company by Id: " + id + "was not found")
				);
		companyRepo.delete(existById);
	}
}
