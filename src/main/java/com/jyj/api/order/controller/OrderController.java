package com.jyj.api.order.controller;

import com.jyj.api.common.response.GenericResponse;
import com.jyj.api.common.validation.ValidationSequence;
import com.jyj.api.order.request.AddOrderRequestDto;
import com.jyj.api.order.request.CancelOrderRequestDto;
import com.jyj.api.order.request.OrderSearchRequestDto;
import com.jyj.api.order.request.UpdateOrderRequestDto;
import com.jyj.api.order.response.OrderSearchResponseDto;
import com.jyj.api.order.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class OrderController {

    private final OrderService orderService;

    @GetMapping("/orders")
    public GenericResponse<List<OrderSearchResponseDto>> orderList(@RequestParam(required = false) String custName,
                                                                   @RequestParam(required = false) String orderNo,
                                                                   @RequestParam(required = false) String status,
                                                                   @RequestParam(required = false) String startDate) {

        OrderSearchRequestDto orderSearchRequestDto = new OrderSearchRequestDto(custName, orderNo, status, startDate);
        List<OrderSearchResponseDto> orders = orderService.findOrders(orderSearchRequestDto);

        return new GenericResponse<>("주문 조회", orders);
    }

    @Transactional
    @PostMapping("/orders")
    public GenericResponse<String> addOder(@Validated(ValidationSequence.class) @RequestBody AddOrderRequestDto addOrderRequestDto) {

        return new GenericResponse<>(
                "주문 생성 완료",
                "orderID : " + orderService.saveOrders(addOrderRequestDto.getOrderBasicInfo(), addOrderRequestDto.getOrderItemInfos())
        );
    }

    @Transactional
    @PutMapping("/orders")
    public GenericResponse<String> updateOrder(@Validated(ValidationSequence.class) @RequestBody UpdateOrderRequestDto updateOrderRequestDto) {

        return new GenericResponse<>(
                "주문 수정 완료",
                "orderID : " + orderService.updateOrders(updateOrderRequestDto.getOrderId(), updateOrderRequestDto.getOrderBasicInfo())
        );
    }

    @Transactional
    @PatchMapping("/orders")
    public GenericResponse<String> cancelOrder(@Validated(ValidationSequence.class) @RequestBody CancelOrderRequestDto cancelOrderRequestDto) {

        return new GenericResponse<>(
                "주문 취소 완료",
                "orderID : " + orderService.cancelOrders(cancelOrderRequestDto.getOrderId())
        );
    }


}
