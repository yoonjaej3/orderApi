package com.jyj.orderapi.exception;

public class NotEnoughStockException extends RuntimeException {
    private static final String MESSAGE = "재고가 없습니다.";

    public NotEnoughStockException() {
        super(MESSAGE);
    }
}
