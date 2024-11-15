/*
 * @ (#) JobSkillServiceImpl.java   1.0     15/11/2024
 *
 * Copyright (c) 2024 IUH. All rights reserved
 */

package vn.edu.iuh.fit.backend.services.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.edu.iuh.fit.backend.models.JobSkill;
import vn.edu.iuh.fit.backend.repositories.JobSkillRepository;
import vn.edu.iuh.fit.backend.services.JobSkillService;

/*
 * @description:
 * @author: Tuss Nguyen
 * @date: 15/11/2024
 * @version: 1.0
 */
@Service
public class JobSkillServiceImpl implements JobSkillService {

    @Autowired
    private JobSkillRepository jobSkillRepository;

    @Override
    public JobSkill save(JobSkill jobSkill) {
        return jobSkillRepository.save(jobSkill);
    }
}
