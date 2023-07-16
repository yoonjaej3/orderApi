package com.jyj.orderapi.service;

import com.jyj.orderapi.entity.Item;
import com.jyj.orderapi.entity.OrderBasicInfo;
import com.jyj.orderapi.entity.OrderItemInfo;
import com.jyj.orderapi.entity.Orders;
import com.jyj.orderapi.entity.enums.OrderStatus;
import com.jyj.orderapi.exception.NotEnoughStockException;
import com.jyj.orderapi.respository.ItemRepository;
import com.jyj.orderapi.respository.OrderRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class OrderServiceTest {

    @Autowired
    OrderService orderService;

    @Autowired
    ItemRepository itemRepository;

    @Autowired
    OrderRepository orderRepository;

    private Item item01;
    private Item item02;

    @BeforeEach
    void setup() {

        item01 = Item.builder()
                .name("코카콜라")
                .price(1000)
                .stockQuantity(10)
                .build();

        item02 = Item.builder()
                .name("치킨")
                .price(20000)
                .stockQuantity(20)
                .build();

        itemRepository.save(item01);
        itemRepository.save(item02);

    }

    @Test
    @Transactional
    @DisplayName("정상 주문생성")
    void saveOrders() {
        //given
        OrderBasicInfo orderBasicInfo = OrderBasicInfo.builder()
                .custName("주윤재")
                .address("파스토")
                .phoneNumber("01099999999")
                .build();

        List<OrderItemInfo> orderItemInfoList = new ArrayList<>();

        orderItemInfoList.add(
                OrderItemInfo.builder()
                        .itemId(item01.getId())
                        .count(5)
                        .build()
        );

        orderItemInfoList.add(
                OrderItemInfo.builder()
                        .itemId(item02.getId())
                        .count(5)
                        .build()
        );

        //when
        Long orderId = orderService.saveOrders(orderBasicInfo, orderItemInfoList);

        //then
        Orders order = orderRepository.getById(orderId);

        Assertions.assertEquals(OrderStatus.PREPARING, order.getStatus(), "상품 주문시 상태는 PREPARING");
        Assertions.assertEquals(5, item01.getStockQuantity(), "상품 주문시 주문 수량만큼 count 감소");

    }

    @Test
    @Transactional
    @DisplayName("재고 부족 예외 발생")
    void saveOrdersException01() {
        //given
        OrderBasicInfo orderBasicInfo = OrderBasicInfo.builder()
                .custName("주윤재")
                .address("파스토")
                .phoneNumber("01099999999")
                .build();

        List<OrderItemInfo> orderItemInfoList = new ArrayList<>();

        orderItemInfoList.add(
                OrderItemInfo.builder()
                        .itemId(item01.getId())
                        .count(15)
                        .build()
        );

        //when then
        Assertions.assertThrows(NotEnoughStockException.class, () -> {
            orderService.saveOrders(orderBasicInfo, orderItemInfoList);
        });

    }

}