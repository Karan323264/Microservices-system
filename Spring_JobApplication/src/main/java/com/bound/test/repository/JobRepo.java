package com.bound.test.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bound.test.entity.Job;

public interface JobRepo extends JpaRepository<Job, Integer>{

}
