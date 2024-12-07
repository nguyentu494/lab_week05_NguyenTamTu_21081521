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
import vn.edu.iuh.fit.backend.services.JobService;

import java.util.Optional;

@Controller
public class CandidateController {

    @Autowired
    private JobService jobService;

    @GetMapping("/candidates")
    public ModelAndView getCandidates(@RequestParam(name = "page") Optional<Integer> page, Authentication authentication) {
        ModelAndView mav = new ModelAndView();
        int currentPage = page.orElse(1);
        Pageable pageable = PageRequest.of(currentPage-1, 3);
        Page<JobSuggestionDTO> jobPage = jobService.findJobsByUsername(pageable, authentication.getName());
        mav.addObject("jobPage", jobPage);
        mav.addObject("totalPages", jobPage.getTotalPages());
        mav.addObject("page", currentPage);
        mav.setViewName("candidate/candidates");
        return mav;
    }
}
