package com.jyj.api.order.entity;

import com.jyj.api.order.exception.NotEnoughCountException;
import com.jyj.api.common.utils.BaseEntity;
import lombok.*;

import javax.persistence.*;

import static javax.persistence.FetchType.LAZY;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class OrderItem extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_item_id")
    private Long id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "item_id")
    private Item item;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "order_id")
    private Orders order;

    private int orderPrice; //주문가격

    private int count; //주문수량

    public static OrderItem createOrderItem(Item item, int orderPrice, int count) {

        if (count < 0) {
            throw new NotEnoughCountException();
        }
        item.order(count);

        return OrderItem.builder()
                .item(item)
                .orderPrice(orderPrice)
                .count(count)
                .build();
    }

    public void setOrder(Orders order) {
        this.order = order;

    }
}
