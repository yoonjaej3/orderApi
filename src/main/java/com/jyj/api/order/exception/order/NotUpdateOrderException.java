package com.jyj.api.order.exception.order;

public class NotUpdateOrderException extends RuntimeException {
    private static final String MESSAGE = "You can only modify the delivery information when the order is in preparation.";

    public NotUpdateOrderException() {
        super(MESSAGE);
    }
}
