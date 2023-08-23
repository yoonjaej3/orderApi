package com.jyj.api.order.exception.order;

public class NotCancelOrderException extends RuntimeException {
    private static final String MESSAGE = "준비중 상태일때는 주문취소 할 수 없습니다.";

    public NotCancelOrderException() {
        super(MESSAGE);
    }
}
