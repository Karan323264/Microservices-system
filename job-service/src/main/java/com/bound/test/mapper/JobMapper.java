package com.bound.test.mapper;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import com.bound.test.dtos.JobDto;
import com.bound.test.entity.Job;

@Mapper(componentModel = "spring")
public interface JobMapper {
	
	@Mapping(target = "id", ignore = true)
	@Mapping(target = "createdAt", ignore = true)
	@Mapping(target = "updatedAt", ignore = true)
	Job toEntity(JobDto requestJob);
	
	JobDto toDto(Job job);
	
	@BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
	@Mapping(target = "id", ignore = true)
	@Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
	void updateEntity(JobDto requestUpdateDto, @MappingTarget Job job);
}
