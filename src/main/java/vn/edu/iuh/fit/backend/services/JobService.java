/*
 * @ (#) JobService.java   1.0     03/11/2024
 *
 * Copyright (c) 2024 IUH. All rights reserved
 */

package vn.edu.iuh.fit.backend.services;

/*
 * @description:
 * @author: Tuss Nguyen
 * @date: 03/11/2024
 * @version: 1.0
 */

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import vn.edu.iuh.fit.backend.models.Job;

import java.util.List;

public interface JobService {
    List<Job> findAll();
    List<Job> findJobByCompanyId(long id);
    Page<Job> findJobByCompanyId(Pageable pageable, long id);

    Job save(Job job);
}