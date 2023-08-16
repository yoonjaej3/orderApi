package com.jyj.api.order.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.jyj.api.order.exception.order.NotUpdateOrderException;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Getter
public class NotUpdateOrderExceptionResponse {
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "systemDefault")
    private LocalDateTime timestamp;
    private int status;
    private String error;

    public NotUpdateOrderExceptionResponse(NotUpdateOrderException e) {
        this.timestamp = LocalDateTime.now();
        this.status = HttpStatus.INTERNAL_SERVER_ERROR.value();
        this.error = e.getMessage();

    }
}
