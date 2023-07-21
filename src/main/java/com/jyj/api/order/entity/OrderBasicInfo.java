package com.jyj.api.order.entity;

import lombok.*;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class OrderBasicInfo {
    private String address;
    private String phoneNumber;
    private String custName;
}
