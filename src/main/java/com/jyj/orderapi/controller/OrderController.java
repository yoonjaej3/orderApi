package com.jyj.orderapi.controller;

import com.jyj.orderapi.request.OrderRequestDto;
import com.jyj.orderapi.request.OrderSearchRequestDto;
import com.jyj.orderapi.response.OrderSearchResponseDto;
import com.jyj.orderapi.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping("/orders")
    public List<OrderSearchResponseDto> orderList(@RequestBody OrderSearchRequestDto orderSearchRequestDto) {

        List<OrderSearchResponseDto> orders = orderService.findOrders(orderSearchRequestDto);

        return orders;
    }

    @Transactional
    @PostMapping("/orders/register")
    public ResponseEntity<String> addOder(@RequestBody OrderRequestDto orderRequestDto) {
        orderService.saveOrders(orderRequestDto.getOrderBasicInfo(), orderRequestDto.getOrderItemInfos());

        return ResponseEntity.ok("Order created successfully");
    }

    @Transactional
    @PutMapping("/orders/{orderId}/cancel")
    public ResponseEntity<String> cancelOrder(@PathVariable Long orderId) {
        orderService.cancelOrders(orderId);

        return ResponseEntity.ok("Order cancelled successfully");
    }

    @Transactional
    @PutMapping("/orders/{orderId}")
    public ResponseEntity<String> updateOrder(@PathVariable Long orderId, @RequestBody OrderRequestDto orderRequestDto) {
        orderService.updateOrders(orderId, orderRequestDto.getOrderBasicInfo());

        return ResponseEntity.ok("Order updated successfully");
    }

}
