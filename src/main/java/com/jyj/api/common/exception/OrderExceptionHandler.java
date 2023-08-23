package com.jyj.api.common.exception;

import com.jyj.api.common.response.ApiErrorResponse;
import com.jyj.api.order.exception.order.NotCancelOrderException;
import com.jyj.api.order.exception.order.NotUpdateOrderException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

@Slf4j
@RestControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
public class OrderExceptionHandler extends GlobalExceptionHandler {
    @ExceptionHandler(NotCancelOrderException.class)
    @ResponseStatus(INTERNAL_SERVER_ERROR)
    public ApiErrorResponse handleNotCancelOrderException(NotCancelOrderException exception, HttpServletRequest request) {
        log.error(exception.getMessage(), exception);

        return ApiErrorResponse.builder()
                .status(INTERNAL_SERVER_ERROR.value())
                .code(INTERNAL_SERVER_ERROR.getReasonPhrase())
                .message(exception.getMessage())
                .url(request.getServletPath())
                .timeStamp(LocalDateTime.now())
                .build();
    }

    @ExceptionHandler(NotUpdateOrderException.class)
    @ResponseStatus(INTERNAL_SERVER_ERROR)
    public ApiErrorResponse handleNotCancelOrderException(NotUpdateOrderException exception, HttpServletRequest request) {
        log.error(exception.getMessage(), exception);

        return ApiErrorResponse.builder()
                .status(INTERNAL_SERVER_ERROR.value())
                .code(INTERNAL_SERVER_ERROR.getReasonPhrase())
                .message(exception.getMessage())
                .url(request.getServletPath())
                .timeStamp(LocalDateTime.now())
                .build();
    }
}
