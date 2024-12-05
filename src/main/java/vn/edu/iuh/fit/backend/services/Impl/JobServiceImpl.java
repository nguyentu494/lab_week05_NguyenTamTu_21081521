/*
 * @ (#) JobServiceImpl.java   1.0     03/11/2024
 *
 * Copyright (c) 2024 IUH. All rights reserved
 */

package vn.edu.iuh.fit.backend.services.Impl;

/*
 * @description:
 * @author: Tuss Nguyen
 * @date: 03/11/2024
 * @version: 1.0
 */

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import vn.edu.iuh.fit.backend.models.Job;
import vn.edu.iuh.fit.backend.repositories.JobRepository;
import vn.edu.iuh.fit.backend.services.JobService;

import java.util.List;

@Service
public class JobServiceImpl implements JobService {

    @Autowired
    private JobRepository jobRepository;

    @Override
    public Page<Job> findAll(int currentPage, int pageSize, String sortField, String sortDir) {
        Sort sort = Sort.by(Sort.Direction.fromString(sortDir), sortField);Sort.by(Sort.Direction.fromString(sortDir), sortField);
        Pageable pageable = PageRequest.of(currentPage - 1, pageSize, sort);

        return jobRepository.findAll(pageable);
    }

    @Override
    public List<Job> findJobByCompanyId(long id) {
        return jobRepository.findJobByCompanyId(id);
    }

    @Override
    public Page<Job> findJobByCompanyId(Pageable pageable, long id) {
        return jobRepository.findJobByCompanyId(pageable, id);
    }

    @Override
    public Page<Job> findJobByCompanyEmail(Pageable pageable, String email) {
        return jobRepository.findJobByCompany_Email(pageable, email);
    }

    @Override
    public Job save(Job job) {
        return jobRepository.save(job);
    }
}
