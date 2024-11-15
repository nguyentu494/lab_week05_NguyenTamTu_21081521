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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import vn.edu.iuh.fit.backend.enums.Role;
import vn.edu.iuh.fit.backend.enums.SkillLevel;
import vn.edu.iuh.fit.backend.enums.SkillType;
import vn.edu.iuh.fit.backend.ids.JobSkillId;
import vn.edu.iuh.fit.backend.models.Company;
import vn.edu.iuh.fit.backend.models.Job;
import vn.edu.iuh.fit.backend.models.JobSkill;
import vn.edu.iuh.fit.backend.models.Skill;
import vn.edu.iuh.fit.backend.services.*;
import vn.edu.iuh.fit.frontend.models.CompanyModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    @RequestMapping(value = {""}, method = RequestMethod.GET)
    public ModelAndView getCompanies(@RequestParam("a") Long id){
        ModelAndView mav = new ModelAndView("companies/companies");
        mav.addObject("account", id);
        mav.addObject("company", companyService.findCompanyByAccount_Id(id));
        return mav;
    }
    @GetMapping("add-job")
    public ModelAndView addJob(@RequestParam("a") Long id) {
        ModelAndView mav = new ModelAndView("companies/add-job");

        // Lấy danh sách kỹ năng từ database
        mav.addObject("skills", skillService.findAll());

        // Tạo danh sách cấp độ kỹ năng (SkillLevel)
        Map<String, Object> skillLevel = new HashMap<>();
        for (SkillLevel r : SkillLevel.values()) {
            skillLevel.put(r.name(), r.name().toLowerCase());
        }
        mav.addObject("skillLevel", skillLevel);
        mav.addObject("account", id);

        // Tạo đối tượng Job mới với danh sách kỹ năng trống
        Job job = new Job();
        job.setJobSkills(new ArrayList<>());
        mav.addObject("job", job);

        return mav;
    }


    @PostMapping("add-job")
    public ModelAndView addJob(@ModelAttribute Job job, @RequestParam("a") Long id, @RequestParam("selSkill1") List<Long> skills, @RequestParam("selSkillLevel1") List<String> skillLevels){
        ModelAndView mav = new ModelAndView("redirect:/companies?a="+id);


        job.setCompany(companyService.findCompanyById(id));
        JobSkill jobSkill = new JobSkill();

        jobService.save(job);
//        List<JobSkill> jobSkills = new ArrayList<>();

        skills.forEach(skillId -> {
            Skill skill = skillService.findById(skillId);
            SkillLevel skillLevel = SkillLevel.valueOf(skillLevels.get(skills.indexOf(skillId)));

            jobSkill.setSkill(skill);
            jobSkill.setSkillLevel(skillLevel);
            jobSkill.setJob(job);
            jobSkill.setId(new JobSkillId(job.getId(), skill.getId()));

            jobSkillService.save(jobSkill);
        });






        return mav;
    }

    @GetMapping("search-candidate")
    public ModelAndView searchCompany(@RequestParam("a") Long id){
        ModelAndView mav = new ModelAndView("companies/search-candidate");
        mav.addObject("account", id);
        return mav;
    }

}
