/*
 * @ (#) SkillService.java   1.0     03/11/2024
 *
 * Copyright (c) 2024 IUH. All rights reserved
 */

package vn.edu.iuh.fit.backend.services;

import vn.edu.iuh.fit.backend.models.Skill;

import java.util.List;

/*
 * @description:
 * @author: Tuss Nguyen
 * @date: 03/11/2024
 * @version: 1.0
 */
public interface SkillService {
    List<Skill> findAll();
    Skill findById(long id);
}
