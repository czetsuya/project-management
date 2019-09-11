package br.com.greenmile.pms.entity;

import lombok.*;

@Data
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PMSFile {

    private String pm;
    private String pmEmail;
    private String pmSkills;
    private String employeeName;
    private String employeeEmail;
    private String employeeSkills;
}
