/*
 * @ (#) CreateJobDTO.java   1.0     08/12/2024
 *
 * Copyright (c) 2024 IUH. All rights reserved
 */

package vn.edu.iuh.fit.backend.dto;

import lombok.*;

import java.util.List;

/*
 * @description:
 * @author: Tuss Nguyen
 * @date: 08/12/2024
 * @version: 1.0
 */

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class CreateJobDTO {
    private String jobName;
    private String jobDescription;
    private long compId;
    private List<Long> skillIds;
    private List<String> skillLevels;
}
