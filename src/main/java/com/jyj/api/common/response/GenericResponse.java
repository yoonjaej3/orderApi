package com.jyj.api.common.response;

import lombok.Getter;

@Getter
public class GenericResponse<T> {
    private String message;
    private T data;

    public GenericResponse(String message, T data) {
        this.message = message;
        this.data = data;
    }
}