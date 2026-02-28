package com.bound.test.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.bound.test.entity.Review;

public interface ReviewRepo extends JpaRepository<Review, Integer>{
	List<Review> findByCompanyId(Integer companyId);
	List<Review> findByJobId(Integer jobId);
}
