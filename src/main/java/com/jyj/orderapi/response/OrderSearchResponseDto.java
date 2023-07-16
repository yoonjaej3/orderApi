package com.jyj.orderapi.response;

import com.jyj.orderapi.entity.enums.OrderStatus;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.time.LocalDateTime;

@Getter
public class OrderSearchResponseDto {
    private final Long orderid;
    private final String orderNo;
    private final String custName;
    private final String phoneNumber;
    private final String address;
    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;
    private final LocalDateTime orderDate;

    @QueryProjection
    public OrderSearchResponseDto(Long id, String orderNo, String custName, String phoneNumber, String address, OrderStatus orderStatus, LocalDateTime orderDate) {
        this.orderid = id;
        this.orderNo = orderNo;
        this.custName = custName;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.orderStatus = orderStatus;
        this.orderDate = orderDate;

    }

}
