/*
 * @ (#) CandidateServiceImpl.java   1.0     10/11/2024
 *
 * Copyright (c) 2024 IUH. All rights reserved
 */

package vn.edu.iuh.fit.backend.services.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.edu.iuh.fit.backend.dto.CandidateMatchDto;
import vn.edu.iuh.fit.backend.models.Candidate;
import vn.edu.iuh.fit.backend.repositories.CandidateRepository;
import vn.edu.iuh.fit.backend.services.CandidateService;

import java.util.List;
import java.util.Optional;

/*
 * @description:
 * @author: Tuss Nguyen
 * @date: 10/11/2024
 * @version: 1.0
 */

@Service
public class CandidateServiceImpl implements CandidateService {
    @Autowired
    private CandidateRepository candidateRepository;
    @Override
    public Candidate findCandidateById(long id) {
        return candidateRepository.findCandidateById(id);
    }

    @Override
    public Candidate save(Candidate candidate) {
        return candidateRepository.save(candidate);
    }

    @Override
    public List<CandidateMatchDto> findCandidatesByJobId(long jobId) {
        return candidateRepository.findCandidatesByJobId(jobId);
    }

    @Override
    public Candidate findByEmail(String email) {
        return candidateRepository.findByEmail(email);
    }

    @Override
    public Candidate findByAccount_Username(String username) {
        return candidateRepository.findByAccount_Username(username);
    }
}
