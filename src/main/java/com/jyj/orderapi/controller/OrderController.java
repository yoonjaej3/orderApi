package com.jyj.orderapi.controller;

import com.jyj.orderapi.request.OrderAddRequestDto;
import com.jyj.orderapi.request.OrderSearchRequestDto;
import com.jyj.orderapi.response.OrderSearchResponseDto;
import com.jyj.orderapi.respository.ItemRepository;
import com.jyj.orderapi.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    //TODO : 주문조회
    //주문 조회
    @PostMapping("/orders")
    public List<OrderSearchResponseDto> orderList(@RequestBody OrderSearchRequestDto orderSearchRequestDto) {

        List<OrderSearchResponseDto> orders = orderService.findOrders(orderSearchRequestDto);

        return orders;

    }

    //TODO : 주문생성
    @Transactional
    @PostMapping("/orders/register")
    public void addOder(@RequestBody OrderAddRequestDto orderAddRequestDto) {

        orderService.saveOrders(orderAddRequestDto.getOrderBasicInfo(), orderAddRequestDto.getOrderItemInfos());
    }

    //TODO : 주문취소

    //TODO : 주문수정

}
