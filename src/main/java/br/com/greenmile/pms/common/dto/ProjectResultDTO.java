package br.com.greenmile.pms.common.dto;

import lombok.*;

import java.util.Date;
import java.util.Set;

@Data
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProjectResultDTO {

    private Long id;
    private String name;
    private Date plannedStart;
    private Date plannedEnd;
    private boolean enabled;
}
