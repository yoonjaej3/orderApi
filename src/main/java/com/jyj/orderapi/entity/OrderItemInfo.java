package com.jyj.orderapi.entity;

import lombok.*;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class OrderItemInfo {
    private Long itemId;
    private int count;
}
