/*
 * @ (#) CandidateController.java   1.0     05/12/2024
 *
 * Copyright (c) 2024 IUH. All rights reserved
 */

package vn.edu.iuh.fit.frontend.controllers;

/*
 * @description:
 * @author: Tuss Nguyen
 * @date: 05/12/2024
 * @version: 1.0
 */

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import vn.edu.iuh.fit.backend.dto.JobSuggestionDTO;
import vn.edu.iuh.fit.backend.models.Candidate;
import vn.edu.iuh.fit.backend.models.Job;
import vn.edu.iuh.fit.backend.services.CandidateService;
import vn.edu.iuh.fit.backend.services.JobService;

import java.security.Principal;
import java.util.Optional;

@Controller
public class CandidateController {
    @Autowired
    private CandidateService candidateService;

    @Autowired
    private JobService jobService;

    @GetMapping("/candidates")
    public ModelAndView getCandidates(@RequestParam(name = "page") Optional<Integer> page, Authentication authentication) {
        ModelAndView mav = new ModelAndView();
        int currentPage = page.orElse(1);

        Candidate candidate = candidateService.findByAccount_Username(authentication.getName());
        mav.addObject("candidate", candidate);
        Pageable pageable = PageRequest.of(currentPage-1, 6);
        Page<JobSuggestionDTO> jobPage = jobService.findJobsByUsername(pageable, authentication.getName());

        mav.addObject("jobPage", jobPage);
        mav.addObject("totalPages", jobPage.getTotalPages());
        mav.addObject("page", currentPage);
        mav.setViewName("candidate/candidates");
        return mav;
    }
    @GetMapping("/candidate/experience")
    public ModelAndView getExperience(Principal principal) {
        ModelAndView mav = new ModelAndView();

        Candidate candidate = candidateService.findByAccount_Username(principal.getName());
        mav.addObject("candidate", candidate);

        mav.setViewName("candidate/experience");
        return mav;
    }

    @GetMapping("/candidates/profile")
    public ModelAndView getCandidate(Principal principal) {
        ModelAndView mav = new ModelAndView();

    Candidate candidate = candidateService.findByAccount_Username(principal.getName());

    mav.addObject("candidate", candidate);

        mav.setViewName("candidate/profile");
        return mav;
    }

    @GetMapping("/candidates/hires")
    public ModelAndView getHires( @RequestParam("page")Optional<Integer> page,
                                  @RequestParam("size")Optional<Integer> size, Principal principal) {
        ModelAndView mav = new ModelAndView();
        int currentPage = page.orElse(1);
        int pageSize = size.orElse(9);

        Candidate candidate = candidateService.findByAccount_Username(principal.getName());
        mav.addObject("candidate", candidate);

        mav.setViewName("candidate/hires");

        Page<Job> jobPage =  jobService.findAll(currentPage, pageSize, "id", "desc");
        int totalPages = jobPage.getTotalPages();

        mav.addObject("jobs", jobPage);
        mav.addObject("page", currentPage);
        mav.addObject("totalPages", totalPages);
        return mav;
    }

    @GetMapping("/candidates/detail-job")
    public ModelAndView getDetailJob(@RequestParam("job") long id, Authentication authentication) {
        ModelAndView mav = new ModelAndView();
        Job job = jobService.findById(id);
        Candidate candidate = candidateService.findByAccount_Username(authentication.getName());
        mav.addObject("candidate", candidate);
        mav.addObject("job", job);
        mav.setViewName("candidate/detail-job");
        return mav;
    }
}
