package com.jyj.api.order.exception.Item;

public class NotEnoughStockException extends RuntimeException {
    private static final String MESSAGE = "Out of stock.";

    public NotEnoughStockException() {
        super(MESSAGE);
    }
}
