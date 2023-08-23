package com.jyj.api.order.exception.order;

public class NotUpdateOrderException extends RuntimeException {
    private static final String MESSAGE = "준비중 상태일때만 배송정보를 수정할 수 있습니다.";

    public NotUpdateOrderException() {
        super(MESSAGE);
    }
}
