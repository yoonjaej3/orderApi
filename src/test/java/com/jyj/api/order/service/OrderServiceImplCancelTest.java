package com.jyj.api.order.service;

import com.jyj.api.order.entity.OrderItem;
import com.jyj.api.order.exception.order.NotCancelOrderException;
import com.jyj.api.order.response.OrderCancelResponseDto;
import com.jyj.api.order.respository.ItemRepository;
import com.jyj.api.order.entity.Item;
import com.jyj.api.order.entity.Orders;
import com.jyj.api.order.entity.enums.OrderStatus;
import com.jyj.api.order.respository.OrderRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class OrderServiceImplCancelTest {

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
    @DisplayName("주문 취소")
    void cancelOrders() {
        //given
        Orders givenOrder = DummyMakeOrder(OrderStatus.PREPARING);

        Long givenId = orderRepository.save(givenOrder).getId();

        //when
        OrderCancelResponseDto orderCancelResponseDto = orderService.cancelOrders(givenId);

        //then
        Orders order = orderRepository.getById(orderCancelResponseDto.getOrderid());

        Assertions.assertEquals(OrderStatus.CANCEL, order.getStatus(), "상품 취소시 상태는 CANCEL");
        Assertions.assertEquals(15, item01.getStockQuantity(), "상품 취소시 취소 수량만큼 count 증가");

    }

    @Test
    @Transactional
    @DisplayName("주문취소 NotCancelOrderException 발생")
    void cancelOrdersException01() {
        //given
        Orders givenOrder = DummyMakeOrder(OrderStatus.DONE);

        Long givenId = orderRepository.save(givenOrder).getId();

        //when then
        Assertions.assertThrows(NotCancelOrderException.class, () -> {
            orderService.cancelOrders(givenId);
        });

    }

    private Orders DummyMakeOrder(OrderStatus preparing) {
        List<OrderItem> orderItems = new ArrayList<>();

        orderItems.add(
                OrderItem.builder()
                        .id(1L)
                        .item(item01)
                        .count(5)
                        .orderPrice(5000)
                        .build()
        );

        orderItems.add(
                OrderItem.builder()
                        .id(2L)
                        .item(item02)
                        .count(5)
                        .orderPrice(100000)
                        .build()
        );

        return Orders.builder()
                .id(1L)
                .orderNo("2023-07-17#주윤재#00001")
                .custName("주윤재")
                .address("파스토")
                .phoneNumber("01099999999")
                .orderItems(orderItems)
                .status(preparing)
                .orderDate(LocalDateTime.parse("2023-07-17T10:55:11.528215"))
                .build();

    }


}