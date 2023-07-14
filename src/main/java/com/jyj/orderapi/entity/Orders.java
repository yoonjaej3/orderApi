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
    @GeneratedValue
    @Column(name = "order_id")
    private Long id;

    private String orderNo; //주문번호

    private String custName;

    private String phoneNumber;

    private String address; //배송주소

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private final List<OrderItem> orderItems = new ArrayList<>();

    @Enumerated(EnumType.STRING)
    private OrderStatus status; //주문상태

    private final LocalDateTime orderDate = LocalDateTime.now(); //주문날짜

    public void makeOrderNo() {
        long orderId = Optional.ofNullable(this.id).orElse(0L);
        this.orderNo = orderDate.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME) + "#" + String.format("%05d", orderId + 1);
    }

    public void basicStatus() {
        this.status = OrderStatus.PREPARING;
    }

}
