package com.bound.test.services;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.bound.test.dtos.JobDto;
import com.bound.test.entity.Job;
import com.bound.test.mapper.JobMapper;
import com.bound.test.repository.JobRepo;

@Service
@RequiredArgsConstructor
public class JobService {

    private final JobRepo repository;
    private final JobMapper mapper;

    public JobDto create(JobDto dto) {
        Job job = mapper.toEntity(dto);
        return mapper.toDto(repository.save(job));
    }

    public List<JobDto> getAll() {
        return repository.findAll()
                .stream()
                .map(mapper::toDto)
                .toList();
    }

    public JobDto getById(Integer id) {
        Job job = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Job not found"));
        return mapper.toDto(job);
    }

    public JobDto update(Integer id, JobDto dto) {
        Job job = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Job not found"));
        mapper.updateEntity(dto, job);
        return mapper.toDto(repository.save(job));
    }

    public void delete(Integer id) {
        repository.deleteById(id);
    }
}
