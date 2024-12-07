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

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class JobSuggestionDTO {
    private String jobName;
    private String description;
    private String companyName;
    private long matchingSkills;
}
