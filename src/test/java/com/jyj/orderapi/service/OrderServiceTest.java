package com.jyj.orderapi.service;

import com.jyj.orderapi.entity.Item;
import com.jyj.orderapi.request.OrderAddRequestDto;
import com.jyj.orderapi.respository.ItemRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
class OrderServiceTest {

    @Autowired
    OrderService orderService;

    @Autowired
    ItemRepository itemRepository;

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
        setup();

        OrderAddRequestDto orderAddRequestDto = OrderAddRequestDto.builder()
                .address("서울시 서초구 파스토")
                .custName("주윤재")
                .phoneNumber("01090626317")
                .itemId(1L)
                .count(2)
                .build();

        //when
        long actual = orderService.saveOrders(orderAddRequestDto);

        //then
        Assertions.assertEquals(1L, actual);
        //Assertions.assertEquals(8, item01.getStockQuantity());

    }

}