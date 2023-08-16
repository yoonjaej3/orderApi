package com.jyj.api.order.exception.order;

public class NotCancelOrderException extends RuntimeException {
    private static final String MESSAGE = "You can only cancel an order when it's in preparation.";

    public NotCancelOrderException() {
        super(MESSAGE);
    }
}
