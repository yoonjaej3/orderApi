package com.jyj.api.order.entity;

import com.jyj.api.order.exception.Item.NotEnoughStockException;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "item_id")
    private Long id;

    @OneToMany(mappedBy = "item", cascade = CascadeType.ALL)
    private final List<OrderItem> orderItems = new ArrayList<>();

    private String name;//상품명

    private int price;//상품가격

    private int stockQuantity;//상품수량

    public void order(int num) {
        this.stockQuantity -= num;

        if (stockQuantity < 0) {
            throw new NotEnoughStockException();
        }
    }

    public void cancel(int num) {
        this.stockQuantity += num;
    }
}
