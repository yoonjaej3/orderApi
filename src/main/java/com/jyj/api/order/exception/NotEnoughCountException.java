package com.jyj.api.order.exception;

public class NotEnoughCountException extends RuntimeException {
    private static final String MESSAGE = "1개 이상의 상품을 주문해야 합니다.";

    public NotEnoughCountException() {
        super(MESSAGE);
    }
}
