/*
 * @ (#) MissJobSkillDTO.java   1.0     08/12/2024
 *
 * Copyright (c) 2024 IUH. All rights reserved
 */

package vn.edu.iuh.fit.backend.dto;

/*
 * @description:
 * @author: Tuss Nguyen
 * @date: 08/12/2024
 * @version: 1.0
 */

import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class MissSkillDTO {
    private long id;
    private String skillName;
}
