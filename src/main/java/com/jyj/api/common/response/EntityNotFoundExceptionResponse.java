package com.jyj.api.common.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDateTime;

@Getter
public class EntityNotFoundExceptionResponse {
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "systemDefault")
    private LocalDateTime timestamp;
    private int status;
    private String error;

    public EntityNotFoundExceptionResponse(EntityNotFoundException e) {
        this.timestamp = LocalDateTime.now();
        this.status = HttpStatus.INTERNAL_SERVER_ERROR.value();
        this.error = e.getMessage();

    }
}
