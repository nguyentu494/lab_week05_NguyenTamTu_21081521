/*
 * @ (#) HomeController.java   1.0     01/12/2024
 *
 * Copyright (c) 2024 IUH. All rights reserved
 */

package vn.edu.iuh.fit.frontend.controllers;

/*
 * @description:
 * @author: Tuss Nguyen
 * @date: 01/12/2024
 * @version: 1.0
 */

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import vn.edu.iuh.fit.backend.dto.JobSuggestionDTO;
import vn.edu.iuh.fit.backend.models.Company;
import vn.edu.iuh.fit.backend.models.Job;
import vn.edu.iuh.fit.backend.services.JobService;

import java.util.Collection;

@Controller
public class HomeController {

    @Autowired
    private JobService jobService;

    @GetMapping({"/", ""})
    public ModelAndView home(Authentication authentication, RedirectAttributes redirectAttributes) {
        ModelAndView mav = new ModelAndView();

        if (authentication != null && authentication.isAuthenticated()) {
            Object principal = authentication.getPrincipal();

            if (principal instanceof UserDetails) {
                UserDetails userDetails = (UserDetails) principal;


                // Lấy các thông tin cơ bản
                String username = userDetails.getUsername();
                Collection<? extends GrantedAuthority> authorities = userDetails.getAuthorities();

                // Log thông tin user

                // Chuyển hướng dựa trên role
                if (authorities.toString().equalsIgnoreCase("[ROLE_COMPANY]")) {
                    mav.setViewName("redirect:/companies");
                    return mav;
                } else if (authorities.toString().equalsIgnoreCase("[ROLE_CANDIDATE]")) {
                    Pageable pageable = PageRequest.of(0, 3);
                    Page<JobSuggestionDTO> jobPage = jobService.findJobsByUsername(pageable, authentication.getName());
                    redirectAttributes.addFlashAttribute("jobPage", jobPage);
                    redirectAttributes.addFlashAttribute("totalPages", jobPage.getTotalPages());
                    mav.setViewName("redirect:/candidates");
                    return mav;
                }
            }
        }

        mav.setViewName("dashboard");
        return mav;
    }

}
