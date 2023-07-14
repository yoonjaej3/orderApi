package com.jyj.orderapi.controller;

import com.jyj.orderapi.request.OrderAddRequestDto;
import com.jyj.orderapi.service.OrderService;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

public class OrderController {

    OrderService orderService;

    //TODO : 주문조회

    //TODO : 주문생성
    @Transactional
    @PostMapping("/orders")
    public void addOder(@RequestBody OrderAddRequestDto orderAddRequestDto) {
        orderService.saveOrders(orderAddRequestDto);
    }

    //TODO : 주문취소

    //TODO : 주문수정

}
