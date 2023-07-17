package com.jyj.orderapi.exception;

public class NotUpdateOrderException extends RuntimeException {
    private static final String MESSAGE = "준비 중일때만 배송정보를 변경할 수 있습니다.";

    public NotUpdateOrderException() {
        super(MESSAGE);
    }
}
