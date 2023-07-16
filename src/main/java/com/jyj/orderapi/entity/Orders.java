package com.jyj.orderapi.entity;

import com.jyj.orderapi.entity.enums.OrderStatus;
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
public class Orders {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private Long id;

    private String orderNo; //주문번호

    private String custName; //고객명

    private String phoneNumber; //고객휴대폰번호

    private String address; //배송주소

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderItem> orderItems = new ArrayList<>();

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

        order.makeOrderNo();
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

    public void makeOrderNo() {
        long orderId = Optional.ofNullable(this.id).orElse(0L);
        this.orderNo = this.orderDate.format(DateTimeFormatter.ISO_LOCAL_DATE) + "#" + String.format("%05d", orderId + 1);
    }

    public static OrderStatus basicStatus() {
        return OrderStatus.PREPARING;
    }
}
