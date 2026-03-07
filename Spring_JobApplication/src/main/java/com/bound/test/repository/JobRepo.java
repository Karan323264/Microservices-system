package com.bound.test.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.bound.test.entity.Job;

public interface JobRepo extends JpaRepository<Job, Integer>{
	 
	 List<Job> findByCompanyId(Integer companyId);
}
