package com.jyj.api.order.controller;

import com.jyj.api.common.response.GenericResponse;
import com.jyj.api.order.request.OrderRequestDto;
import com.jyj.api.order.request.OrderSearchRequestDto;
import com.jyj.api.order.response.OrderSearchResponseDto;
import com.jyj.api.order.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.Transactional;
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
    public GenericResponse<String> addOder(@RequestBody OrderRequestDto orderRequestDto) {

        return new GenericResponse<>(
                "주문 생성 완료",
                "orderID : " + orderService.saveOrders(orderRequestDto.getOrderBasicInfo(), orderRequestDto.getOrderItemInfos())
        );
    }

    @Transactional
    @PutMapping("/orders")
    public GenericResponse<String> updateOrder(@RequestBody OrderRequestDto orderRequestDto) {


        return new GenericResponse<>(
                "주문 수정 완료",
                "orderID : " + orderService.updateOrders(orderRequestDto.getOrderId(), orderRequestDto.getOrderBasicInfo())
        );
    }

    @Transactional
    @PatchMapping("/orders")
    public GenericResponse<String> cancelOrder(@RequestBody OrderRequestDto orderRequestDto) {

        return new GenericResponse<>(
                "주문 취소 완료",
                "orderID : " + orderService.cancelOrders(orderRequestDto.getOrderId())
        );
    }


}
