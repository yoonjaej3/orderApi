package com.jyj.api.common.exception;

import com.jyj.api.common.response.EntityNotFoundExceptionResponse;
import com.jyj.api.common.response.GenericResponse;
import com.jyj.api.common.response.MethodArgumentNotValidExceptionResponse;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.persistence.EntityNotFoundException;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(value = EntityNotFoundException.class)
    public GenericResponse<EntityNotFoundExceptionResponse> EntityNotFoundException(EntityNotFoundException e) {
        return new GenericResponse<>("EntityNotFoundException", new EntityNotFoundExceptionResponse(e));
    }

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public GenericResponse<MethodArgumentNotValidExceptionResponse> MethodArgumentNotValidException(MethodArgumentNotValidException e) {
        return new GenericResponse<>("MethodArgumentNotValidException", new MethodArgumentNotValidExceptionResponse(e));
    }
}
