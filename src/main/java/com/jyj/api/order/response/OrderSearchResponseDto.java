package com.jyj.api.order.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.jyj.api.order.entity.enums.OrderStatus;
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
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
    private final LocalDateTime orderDate;
    private final String itemName;
    private final int totalPrice;
    private final int count;

    @QueryProjection
    public OrderSearchResponseDto(Long id, String orderNo, String custName, String phoneNumber, String address, OrderStatus orderStatus
            , LocalDateTime orderDate, String itemName, int count, int totalPrice) {
        this.orderid = id;
        this.orderNo = orderNo;
        this.custName = custName;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.orderStatus = orderStatus;
        this.orderDate = orderDate;
        this.itemName = itemName;
        this.count = count;
        this.totalPrice = totalPrice;

    }

}
