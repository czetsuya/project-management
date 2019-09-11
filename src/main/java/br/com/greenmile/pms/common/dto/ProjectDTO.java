package br.com.greenmile.pms.common.dto;

import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.util.Date;

@Data
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProjectDTO {

    @NotEmpty(message = "Name can not be empty")
    @Length(min = 3, max = 100, message = "Name size must be between 3 and 100")
    private String name;

    @NotEmpty(message = "PlannedStart can not be empty")
    private Date plannedStart;

    @NotEmpty(message = "PlannedStart can not be empty")
    private Date plannedEnd;

    private boolean enabled;
}
