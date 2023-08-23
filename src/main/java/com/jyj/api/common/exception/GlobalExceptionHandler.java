package com.jyj.api.common.exception;

import com.jyj.api.common.response.ApiErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {
    private static final String COMMON_ERROR_MESSAGE = "에러가 발생했습니다. 이 증상이 계속될 경우 담당자에게 연락해주세요.";

    @ExceptionHandler(value = Exception.class)
    @ResponseStatus(INTERNAL_SERVER_ERROR)
    public ApiErrorResponse Exception(Exception exception, HttpServletRequest request) {
        log.error(exception.getMessage(), exception);

        return ApiErrorResponse.builder()
                .status(INTERNAL_SERVER_ERROR.value())
                .code(INTERNAL_SERVER_ERROR.getReasonPhrase())
                .message(COMMON_ERROR_MESSAGE)
                .url(request.getServletPath())
                .timeStamp(LocalDateTime.now())
                .build();
    }

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    @ResponseStatus(BAD_REQUEST)
    public ApiErrorResponse MethodArgumentNotValidException(MethodArgumentNotValidException exception, HttpServletRequest request) {
        log.error(exception.getMessage(), exception);

        Map<String, String> errors = new HashMap<>();
        exception.getBindingResult().getAllErrors().forEach(error -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });

        return ApiErrorResponse.builder()
                .status(BAD_REQUEST.value())
                .code(BAD_REQUEST.getReasonPhrase())
                .message(exception.getMessage())
                .url(request.getServletPath())
                .timeStamp(LocalDateTime.now())
                .errors(errors)
                .build();
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseStatus(BAD_REQUEST)
    public ApiErrorResponse NotReadableExceptionResponse(HttpMessageNotReadableException exception, HttpServletRequest request) {
        log.error(exception.getMessage(), exception);

        return ApiErrorResponse.builder()
                .status(BAD_REQUEST.value())
                .code(BAD_REQUEST.getReasonPhrase())
                .message(exception.getMessage())
                .url(request.getServletPath())
                .timeStamp(LocalDateTime.now())
                .build();
    }

}
