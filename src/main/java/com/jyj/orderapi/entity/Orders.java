package com.jyj.orderapi.entity;

import com.jyj.orderapi.entity.enums.OrderStatus;
import com.jyj.orderapi.exception.NotCancelOrderException;
import com.jyj.orderapi.exception.NotUpdateOrderException;
import com.jyj.orderapi.utils.BaseEntity;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Orders extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private Long id;

    private String orderNo; //주문번호

    private String custName; //고객명

    private String phoneNumber; //고객휴대폰번호

    private String address; //배송주소

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderItem> orderItems;

    @Enumerated(EnumType.STRING)
    private OrderStatus status; //주문상태

    private LocalDateTime orderDate; //주문날짜

    public static Orders createOrder(OrderBasicInfo orderBasicInfo, List<OrderItem> orderItems) {
        Orders order = Orders.builder()
                .custName(orderBasicInfo.getCustName())
                .phoneNumber(orderBasicInfo.getPhoneNumber())
                .address(orderBasicInfo.getAddress())
                .status(basicStatus())
                .orderDate(LocalDateTime.now())
                .build();

        order.addOrderItem(orderItems);

        return order;
    }

    public void addOrderItem(List<OrderItem> orderItems) {
        List<OrderItem> orderItemList = new ArrayList<>();

        for (OrderItem orderItem : orderItems) {
            orderItemList.add(orderItem);
            orderItem.setOrder(this);
        }

        this.orderItems = orderItemList;
    }

    public Orders cancel(Orders order) {
        if (!order.getStatus().equals(OrderStatus.PREPARING))
            throw new NotCancelOrderException();

        for (OrderItem orderItem : order.getOrderItems()) {
            orderItem.getItem().cancel(orderItem.getCount());
        }

        this.status = OrderStatus.CANCEL;

        return this;
    }

    public Orders updateOrder(OrderBasicInfo orderBasicInfo) {
        if (!this.getStatus().equals(OrderStatus.PREPARING))
            throw new NotUpdateOrderException();

        this.address = Optional.ofNullable(orderBasicInfo.getAddress()).orElse(this.address);
        this.phoneNumber = Optional.ofNullable(orderBasicInfo.getPhoneNumber()).orElse(this.phoneNumber);

        return this;
    }

    public void makeOrderNo(int count) {
        this.orderNo = this.orderDate.format(DateTimeFormatter.ISO_LOCAL_DATE)
                + "#" + this.custName
                + "#" + String.format("%05d", count);
    }

    public static OrderStatus basicStatus() {
        return OrderStatus.PREPARING;
    }

}
