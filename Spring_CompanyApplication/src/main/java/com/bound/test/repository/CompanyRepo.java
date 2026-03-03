package com.bound.test.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.bound.test.entity.Company;

public interface CompanyRepo extends JpaRepository<Company, Integer>{

}
