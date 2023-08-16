package com.jyj.api.common.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Getter
public class MethodArgumentNotValidExceptionResponse {
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "systemDefault")
    private LocalDateTime timestamp;
    private int status;
    private Map<String, String> errors = new HashMap<>();

    public MethodArgumentNotValidExceptionResponse(MethodArgumentNotValidException e) {
        this.timestamp = LocalDateTime.now();
        this.status = HttpStatus.BAD_REQUEST.value();

        e.getBindingResult().getAllErrors().forEach(error -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
    }
}
