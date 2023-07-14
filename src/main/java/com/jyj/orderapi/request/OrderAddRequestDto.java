package com.jyj.orderapi.request;

import com.jyj.orderapi.entity.Orders;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class OrderAddRequestDto {

    private String custName;
    private String phoneNumber;
    private String address;
    private Long itemId;
    private int count;

}
