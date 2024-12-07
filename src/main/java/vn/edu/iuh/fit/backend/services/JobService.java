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
import vn.edu.iuh.fit.backend.dto.JobSuggestionDTO;
import vn.edu.iuh.fit.backend.models.Job;

import java.util.List;

public interface JobService {
    Page<Job> findAll(int currentPage, int pageSize, String sortField, String sortDir);
    List<Job> findJobByCompanyId(long id);
    Page<Job> findJobByCompanyId(Pageable pageable, long id);
    Page<Job> findJobByCompanyEmail(Pageable pageable, String email);
    Job save(Job job);
    Page<JobSuggestionDTO> findJobsByUsername(Pageable pageable, String username);

}
