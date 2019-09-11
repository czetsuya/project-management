package br.com.greenmile.pms.common.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.ConstraintViolationException;
import java.util.Date;

@RestController
@ControllerAdvice
public class CustomResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity handleConstraintViolationException(ConstraintViolationException exception, WebRequest request) {
        ResponseError error = ResponseError.builder()
                .timestamp(new Date())
                .status(HttpStatus.BAD_REQUEST.value())
                .error(HttpStatus.BAD_REQUEST.getReasonPhrase())
                .message(formatMessage(exception.getMessage()))
                .path(formatURI(request.getDescription(false)))
                .build();
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    private String formatMessage(String str) {
        if (StringUtils.isEmpty(str)) {
            return str;
        }

        String[] strings = str.split(":");

        if (strings.length == 2) {
            return strings[1].trim();
        }
        return str;
    }

    private String formatURI(String str) {
        if (StringUtils.isEmpty(str)) {
            return str;
        }
        return str.replace("uri=", "");
    }
}
