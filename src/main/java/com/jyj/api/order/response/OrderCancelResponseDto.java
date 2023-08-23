package com.jyj.api.order.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class OrderCancelResponseDto {
    private final Long orderid;

}
