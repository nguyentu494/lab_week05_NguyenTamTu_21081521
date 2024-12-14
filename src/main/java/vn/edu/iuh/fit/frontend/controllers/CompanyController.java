/*
 * @ (#) CompanyController.java   1.0     03/11/2024
 *
 * Copyright (c) 2024 IUH. All rights reserved
 */

package vn.edu.iuh.fit.frontend.controllers;

/*
 * @description:
 * @author: Tuss Nguyen
 * @date: 03/11/2024
 * @version: 1.0
 */

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import vn.edu.iuh.fit.backend.dto.CandidateMatchDto;
import vn.edu.iuh.fit.backend.dto.CreateJobDTO;
import vn.edu.iuh.fit.backend.dto.RegisterCompanyDTO;
import vn.edu.iuh.fit.backend.enums.Role;
import vn.edu.iuh.fit.backend.enums.SkillLevel;
import vn.edu.iuh.fit.backend.enums.SkillType;
import vn.edu.iuh.fit.backend.ids.JobSkillId;
import vn.edu.iuh.fit.backend.models.*;
import vn.edu.iuh.fit.backend.services.*;
import vn.edu.iuh.fit.frontend.models.CompanyModel;

import java.security.Principal;
import java.util.*;

@Controller
@RequestMapping("companies")
public class CompanyController {

    @Autowired
    CompanyService companyService;
    @Autowired
    AccountService accountService;
    @Autowired
    SkillService skillService;
    @Autowired
    JobService jobService;
    @Autowired
    JobSkillService jobSkillService;
    @Autowired
    CandidateService candidateService;
    @Autowired
    SendEmailService sendEmailService;

    @RequestMapping(value = {""}, method = RequestMethod.GET)
    public ModelAndView getCompanies(HttpSession session, @RequestParam("page")Optional<Integer> page,
                                     @RequestParam("size")Optional<Integer> size, Authentication authentication){

        int currentPage = page.orElse(1);
        int pageSize = size.orElse(12);
//        Page<Job> jobPage = jobService.findAll(PageRequest.of(currentPage - 1, pageSize));

//        Company company = (Company) session.getAttribute("account");
        RegisterCompanyDTO company = companyService.findCompanyDTOByUsername(authentication.getName());
//        System.out.println(company);
        ModelAndView mav = new ModelAndView("companies/companies");

        Page<Job> jobPage =  jobService.findAll(currentPage, pageSize, "id", "desc");
        int totalPages = jobPage.getTotalPages();

        List<String> colors = List.of("#007BFF", "#6C757D", "#28A745", "#DC3545", "#FFC107", "#17A2B8");

        mav.addObject("colors", colors);

        mav.addObject("company", company);
        mav.addObject("jobs", jobPage);
        mav.addObject("page", currentPage);
        mav.addObject("totalPages", totalPages);
        return mav;
    }
    @GetMapping("add-job")
    public ModelAndView addJob() {
        ModelAndView mav = new ModelAndView("companies/add-job");

        // Lấy danh sách kỹ năng từ database
        mav.addObject("skills", skillService.findAll());

        // Tạo danh sách cấp độ kỹ năng (SkillLevel)
        Map<String, Object> skillLevel = new HashMap<>();
        for (SkillLevel r : SkillLevel.values()) {
            skillLevel.put(r.name(), r.name().toLowerCase());
        }
        mav.addObject("skillLevel", skillLevel);
        // Tạo đối tượng Job mới với danh sách kỹ năng trống
        CreateJobDTO job = new CreateJobDTO();
        mav.addObject("job", job);

        return mav;
    }


    @PostMapping("add-job")
    public ModelAndView addJob(@ModelAttribute CreateJobDTO job, Principal principal) {
        ModelAndView mav = new ModelAndView("redirect:/companies");
        RegisterCompanyDTO company = companyService.findCompanyDTOByUsername(principal.getName());

        jobService.save(job, principal);

        Page<Job> jobPage =  jobService.findAll(1, 12, "id", "desc");
        int totalPages = jobPage.getTotalPages();

        mav.addObject("company", company);

        mav.addObject("jobs", jobPage);
        mav.addObject("page", 1);
        mav.addObject("totalPages", totalPages);

        return mav;
    }

    @GetMapping("search-candidate")
    public ModelAndView searchCompany(@RequestParam("page")Optional<Integer> page,
                                      @RequestParam("size")Optional<Integer> size, Authentication authentication){
        ModelAndView mav = new ModelAndView("companies/search-candidate");
        int currentPage = page.orElse(1);
        int pageSize = size.orElse(12);
        RegisterCompanyDTO company = companyService.findCompanyDTOByUsername(authentication.getName());

//        mav.addObject("account", id);
        Pageable pageable = PageRequest.of(currentPage-1, pageSize);
        Page<Job> jobPage =  jobService.findJobByCompanyEmail(pageable, company.getEmail());


        Map<Job, List<CandidateMatchDto>> jobCandidatesMap = new HashMap<>();

        for (Job job : jobPage.getContent()) {
            System.out.println(job.getId());
            List<CandidateMatchDto> candidateMatchDto = new ArrayList<>();
            candidateMatchDto = candidateService.findCandidatesByJobId(job.getId());
            jobCandidatesMap.put(job, candidateMatchDto); // Đưa vào Map
        }
//        System.out.println(jobCandidatesMap);


        int totalPages = jobPage.getTotalPages();

        mav.addObject("company", company);
        mav.addObject("jobs", jobCandidatesMap);
        mav.addObject("page", currentPage);
        mav.addObject("totalPages", totalPages);

        return mav;
    }

    @PostMapping("/invite")
    public ModelAndView inviteCandidate(@RequestParam("email") String email, @RequestParam("job") String jobName, Authentication authentication,
                                        RedirectAttributes redirectAttributes){
        ModelAndView mav = new ModelAndView("redirect:/companies/search-candidate");
        RegisterCompanyDTO company = companyService.findCompanyDTOByUsername(authentication.getName());
        Candidate candidate = candidateService.findByEmail(email);
        String subject = "Invitation to apply for job";
        String content = "Dear " + candidate.getFullName() + ",\n" +
                "We would like to invite you to apply for the job " + jobName + " at our company.\n" +
                "Please confirm your job offer via this email:\n" + company.getEmail() + "\n" +
                "Best regards,\n" +
                company.getCompName();

        sendEmailService.sendEmail(email, subject, content);


        redirectAttributes.addFlashAttribute("success", "Invitation sent to " + email + " successfully!");
        return mav;
    }

    @GetMapping("manage")
    public ModelAndView ManageCompany(@RequestParam("page")Optional<Integer> page,
                                     @RequestParam("size")Optional<Integer> size, Authentication authentication){

        int currentPage = page.orElse(1);
        int pageSize = size.orElse(3);
//        Page<Job> jobPage = jobService.findAll(PageRequest.of(currentPage - 1, pageSize));

//        Company company = (Company) session.getAttribute("account");
        RegisterCompanyDTO company = companyService.findCompanyDTOByUsername(authentication.getName());
//        System.out.println(company);
        ModelAndView mav = new ModelAndView("companies/companies");

        Page<Job> jobPage =  jobService.findJobByCompanyEmail(PageRequest.of(currentPage - 1, pageSize), company.getEmail());
        int totalPages = jobPage.getTotalPages();
        List<String> colors = List.of("#007BFF", "#6C757D", "#28A745", "#DC3545", "#FFC107", "#17A2B8");

        mav.addObject("colors", colors);

        mav.addObject("status", "manage");
        mav.addObject("company", company);
        mav.addObject("jobs", jobPage);
        mav.addObject("page", currentPage);
        mav.addObject("totalPages", totalPages);
        return mav;
    }

    @GetMapping("profile")
    public ModelAndView getProfile(Authentication authentication){
        ModelAndView mav = new ModelAndView("companies/profile");
        RegisterCompanyDTO company = companyService.findCompanyDTOByUsername(authentication.getName());
        mav.addObject("company", company);
        return mav;
    }

}
