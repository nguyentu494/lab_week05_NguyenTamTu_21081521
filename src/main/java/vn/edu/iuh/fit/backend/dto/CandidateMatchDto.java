/*
 * @ (#) CandidateMatchDto.java   1.0     05/12/2024
 *
 * Copyright (c) 2024 IUH. All rights reserved
 */

package vn.edu.iuh.fit.backend.dto;

/*
 * @description:
 * @author: Tuss Nguyen
 * @date: 05/12/2024
 * @version: 1.0
 */

import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class CandidateMatchDto {
    private String fullName;
    private String email;
    private long id;
    private long matchSkill;
}
