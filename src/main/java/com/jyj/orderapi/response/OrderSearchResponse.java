package com.jyj.orderapi.response;

import com.jyj.orderapi.entity.Item;
import com.jyj.orderapi.entity.enums.OrderStatus;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.time.LocalDateTime;

@Getter
public class OrderSearchResponse {
    private Long orderid;
    private String orderNo;
    private String custName;
    private String phoneNumber;
    private String address;
    private Long itemId;
    private String itemName;
    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;
    private LocalDateTime orderDate;

    @QueryProjection
    public OrderSearchResponse(Long id, String orderNo, String custName, String phoneNumber, String address, Item item, OrderStatus orderStatus, LocalDateTime orderDate) {
        this.orderid = id;
        this.orderNo = orderNo;
        this.custName = custName;
        this.itemId = item.getId();
        this.itemName = item.getName();
        this.orderStatus = orderStatus;
        this.orderDate = orderDate;

    }

}
