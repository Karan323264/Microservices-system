package com.bound.test.mapper;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import com.bound.test.dtos.ReviewDto;
import com.bound.test.entity.Review;

@Mapper(componentModel = "spring")
public interface ReviewMapper {
	
	@Mapping(target = "id", ignore = true)
	@Mapping(target = "createdAt", ignore = true)
	@Mapping(target = "updatedAt", ignore = true)
	Review toEntity(ReviewDto requestReview);
	
	ReviewDto toDto(Review review);
	
	@BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
	@Mapping(target = "id", ignore = true)
	@Mapping(target = "createdAt", ignore = true)
	@Mapping(target = "updatedAt", ignore = true)
	void toUpdate(ReviewDto reviewUpdateRequest, @MappingTarget Review review);
}
