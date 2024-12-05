/*
 * @ (#) CandidateService.java   1.0     10/11/2024
 *
 * Copyright (c) 2024 IUH. All rights reserved
 */

package vn.edu.iuh.fit.backend.services;

import vn.edu.iuh.fit.backend.dto.CandidateMatchDto;
import vn.edu.iuh.fit.backend.models.Candidate;
import vn.edu.iuh.fit.backend.models.Company;

import java.util.List;
import java.util.Optional;

/*
 * @description:
 * @author: Tuss Nguyen
 * @date: 10/11/2024
 * @version: 1.0
 */
public interface CandidateService {
    Candidate findCandidateById(long id);
    Candidate save(Candidate candidate);
    List<CandidateMatchDto> findCandidatesByJobId(long jobId);
    Candidate findByEmail(String email);
}
