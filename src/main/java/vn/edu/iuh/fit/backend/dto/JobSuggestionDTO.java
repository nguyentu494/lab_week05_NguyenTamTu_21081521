/*
 * @ (#) JobSuggestionDTO.java   1.0     06/12/2024
 *
 * Copyright (c) 2024 IUH. All rights reserved
 */

package vn.edu.iuh.fit.backend.dto;

/*
 * @description:
 * @author: Tuss Nguyen
 * @date: 06/12/2024
 * @version: 1.0
 */

import lombok.*;
import vn.edu.iuh.fit.backend.models.Skill;

import java.util.List;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class JobSuggestionDTO {
    private long id;
    private String jobName;
    private String description;
    private String companyName;
    private long matchingSkills;
    private List<String> missSkills;

    public JobSuggestionDTO(long id, String jobName, String description, String companyName, long matchingSkills) {
        this.id = id;
        this.jobName = jobName;
        this.description = description;
        this.companyName = companyName;
        this.matchingSkills = matchingSkills;
    }
}
