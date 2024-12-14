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
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import vn.edu.iuh.fit.backend.dto.CreateJobDTO;
import vn.edu.iuh.fit.backend.dto.JobSuggestionDTO;
import vn.edu.iuh.fit.backend.dto.MissSkillDTO;
import vn.edu.iuh.fit.backend.enums.SkillLevel;
import vn.edu.iuh.fit.backend.ids.JobSkillId;
import vn.edu.iuh.fit.backend.models.*;
import vn.edu.iuh.fit.backend.repositories.CompanyRepository;
import vn.edu.iuh.fit.backend.repositories.JobRepository;
import vn.edu.iuh.fit.backend.repositories.JobSkillRepository;
import vn.edu.iuh.fit.backend.services.*;

import java.security.Principal;
import java.util.List;

@Service
public class JobServiceImpl implements JobService {

    @Autowired
    private JobRepository jobRepository;
    @Autowired
    private CandidateService candidateService;
    @Autowired
    private CompanyRepository companyRepository;
    @Autowired
    private SkillService skillService;
    @Autowired
    private JobSkillRepository jobSkillRepository;

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
    public Job save(CreateJobDTO jobDTO, Principal principal) {

        Company company = companyRepository.findCompanyByAccount_Username(principal.getName());
        Job job = new Job();
        job.setJobName(jobDTO.getJobName());
        job.setJobDesc(jobDTO.getJobDescription());
        job.setCompany(company);
        jobRepository.save(job);
        List<JobSkill> jobSkills = new java.util.ArrayList<>();
        for(int i = 0; i < jobDTO.getSkillIds().size(); i++){
            JobSkill jobSkill = new JobSkill();
            Skill skill = skillService.findById(jobDTO.getSkillIds().get(i));
            jobSkill.setSkill(skill);
            jobSkill.setJob(job);
            jobSkill.setSkillLevel(SkillLevel.valueOf(jobDTO.getSkillLevels().get(i)));
            jobSkill.setId(new JobSkillId(job.getId(), skill.getId()));
            jobSkills.add(jobSkill);
        }
        job.setJobSkills(jobSkills);
        jobSkillRepository.saveAll(jobSkills);
        return job;
    }


    @Override
    public Page<JobSuggestionDTO> findJobsByUsername(Pageable pageable, String username) {
        Candidate candidate = candidateService.findByAccount_Username(username);
        Page<JobSuggestionDTO> jobSuggestionDTOS = jobRepository.findJobsByCandidateId(pageable, candidate.getId());
        List<MissSkillDTO> missSkillDTOS = jobRepository.findJobSkillsForCandidate(candidate.getId());
        for(JobSuggestionDTO jobSuggestionDTO : jobSuggestionDTOS){
            for(MissSkillDTO missSkillDTO : missSkillDTOS){
                if(jobSuggestionDTO.getId() == missSkillDTO.getId()){
                    jobSuggestionDTO.setMissSkills(jobSuggestionDTO.getMissSkills() == null ? new java.util.ArrayList<>() : jobSuggestionDTO.getMissSkills());
                    jobSuggestionDTO.getMissSkills().add(missSkillDTO.getSkillName());
                }
            }
        }
        return jobSuggestionDTOS;
    }

    @Override
    public Page<Job> findJobsByUser(Pageable pageable, String username) {
        Company company = companyRepository.findCompanyByAccount_Username(username);
        return jobRepository.findJobByCompany_Email(pageable, company.getEmail());
    }

    @Override
    public Job findById(long id) {
        return jobRepository.findJobById(id);
    }
}
