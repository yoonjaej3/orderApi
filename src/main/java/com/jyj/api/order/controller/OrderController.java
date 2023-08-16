package com.jyj.api.order.controller;

import com.jyj.api.common.response.GenericResponse;
import com.jyj.api.common.validation.ValidationSequence;
import com.jyj.api.order.exception.order.NotCancelOrderException;
import com.jyj.api.order.exception.order.NotUpdateOrderException;
import com.jyj.api.order.request.AddOrderRequestDto;
import com.jyj.api.order.request.CancelOrderRequestDto;
import com.jyj.api.order.request.OrderSearchRequestDto;
import com.jyj.api.order.request.UpdateOrderRequestDto;
import com.jyj.api.order.response.NotCancelOrderExceptionResponse;
import com.jyj.api.order.response.NotUpdateOrderExceptionResponse;
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

        return new GenericResponse<>("Find Orders", orders);
    }

    @Transactional
    @PostMapping("/orders")
    public GenericResponse<String> saveOder(@Validated(ValidationSequence.class) @RequestBody AddOrderRequestDto addOrderRequestDto) {

        return new GenericResponse<>(
                "Save Order",
                "orderID : " + orderService.saveOrders(addOrderRequestDto.getOrderBasicInfo(), addOrderRequestDto.getOrderItemInfos())
        );
    }

    @Transactional
    @PutMapping("/orders")
    public GenericResponse<String> updateOrder(@Validated(ValidationSequence.class) @RequestBody UpdateOrderRequestDto updateOrderRequestDto) {

        return new GenericResponse<>(
                "Update Order",
                "orderID : " + orderService.updateOrders(updateOrderRequestDto.getOrderId(), updateOrderRequestDto.getOrderBasicInfo())
        );
    }

    @Transactional
    @PatchMapping("/orders")
    public GenericResponse<String> cancelOrder(@Validated(ValidationSequence.class) @RequestBody CancelOrderRequestDto cancelOrderRequestDto) {

        return new GenericResponse<>(
                "Cancel Order",
                "orderID : " + orderService.cancelOrders(cancelOrderRequestDto.getOrderId())
        );
    }

    @ExceptionHandler(NotCancelOrderException.class)
    public GenericResponse<NotCancelOrderExceptionResponse> handleNotCancelOrderException(NotCancelOrderException e) {

        return new GenericResponse<>(
                "NotCancelOrderException", new NotCancelOrderExceptionResponse(e)
        );
    }

    @ExceptionHandler(NotUpdateOrderException.class)
    public GenericResponse<NotUpdateOrderExceptionResponse> handleNotCancelOrderException(NotUpdateOrderException e) {

        return new GenericResponse<>(
                "NotUpdateOrderException", new NotUpdateOrderExceptionResponse(e)
        );
    }

}
