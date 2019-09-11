package br.com.greenmile.pms.common.dto;

import lombok.*;

import java.util.Set;

@Data
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserResultDTO {

    private Long id;
    private String name;
    private String email;
    private Set<SkillResultDTO> skills;
}
