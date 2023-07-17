package com.jyj.orderapi.exception;

public class NotFoundOrderException extends RuntimeException {
    private static final String MESSAGE = "해당 주문을 찾을 수 없습니다.";

    public NotFoundOrderException() {
        super(MESSAGE);
    }
}
