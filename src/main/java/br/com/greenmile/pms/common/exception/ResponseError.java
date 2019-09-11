package br.com.greenmile.pms.common.exception;

import lombok.*;

import java.util.Date;

@Data
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ResponseError {

    private Date timestamp;
    private int status;
    private String error;
    private String message;
    private String path;
}
