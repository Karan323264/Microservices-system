package com.bound.test.controller;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import com.bound.test.dtos.JobDto;
import com.bound.test.services.JobService;

@RestController
@RequestMapping("/jobs")
@RequiredArgsConstructor
public class JobController {

    private final JobService service;

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public JobDto create(@RequestBody JobDto dto) {
        return service.create(dto);
    }

    @GetMapping
    public List<JobDto> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public JobDto getById(@PathVariable Integer id) {
        return service.getById(id);
    }

    @PutMapping("/update/{id}")
    public JobDto update(@PathVariable Integer id, @RequestBody JobDto dto) {
        return service.update(id, dto);
    }

    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Integer id) {
        service.delete(id);
    }
}
