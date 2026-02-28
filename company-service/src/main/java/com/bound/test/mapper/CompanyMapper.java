package com.bound.test.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import com.bound.test.dtos.CompanyDto;
import com.bound.test.entity.Company;

@Mapper(componentModel = "spring")
public interface CompanyMapper {
	
	@Mapping(target = "id", ignore = true)
	@Mapping(target = "createdAt", ignore = true)
	@Mapping(target = "updatedAt", ignore = true)
	Company toEntity(CompanyDto requestCompany);
	
	CompanyDto toDto(Company company);
	
	@Mapping(target = "id", ignore = true)
	@Mapping(target = "createdAt", ignore = true)
	@Mapping(target = "updatedAt", ignore = true)
	void	toUpdate(CompanyDto requestUpdate, @MappingTarget Company company);
}
