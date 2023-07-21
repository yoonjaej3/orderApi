package com.jyj.api.order.exception;

public class NotCancelOrderException extends RuntimeException {
    private static final String MESSAGE = "준비 중일때만 주문취소 할 수 있습니다.";

    public NotCancelOrderException() {
        super(MESSAGE);
    }
}
