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
import org.springframework.data.domain.Pageable;
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
    public List<Job> findAll() {
        return null;
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
    public Job save(Job job) {
        return jobRepository.save(job);
    }
}
