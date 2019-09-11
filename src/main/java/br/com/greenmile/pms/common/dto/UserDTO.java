package br.com.greenmile.pms.common.dto;

import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

@Data
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {

    @NotEmpty(message = "Name can not be empty")
    @Length(min = 3, max = 100, message = "Name size must be between 3 and 100")
    private String name;

    @NotEmpty(message = "Email can not be empty")
    @Email(message = "Invalid email address")
    private String email;
}
