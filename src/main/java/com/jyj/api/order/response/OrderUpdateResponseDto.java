package com.jyj.api.order.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class OrderUpdateResponseDto {
    private final Long orderid;

}
