package com.jyj.api.order.entity;

import lombok.*;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class OrderItemInfo {
    private Long itemId;
    private int count;
}
