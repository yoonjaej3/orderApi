package com.jyj.api.order.controller;

import com.jyj.api.common.response.GenericResponse;
import com.jyj.api.common.validation.ValidationSequence;
import com.jyj.api.order.request.AddOrderRequestDto;
import com.jyj.api.order.request.CancelOrderRequestDto;
import com.jyj.api.order.request.OrderSearchRequestDto;
import com.jyj.api.order.request.UpdateOrderRequestDto;
import com.jyj.api.order.response.OrderCancelResponseDto;
import com.jyj.api.order.response.OrderSaveResponseDto;
import com.jyj.api.order.response.OrderSearchResponseDto;
import com.jyj.api.order.response.OrderUpdateResponseDto;
import com.jyj.api.order.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class OrderApiController {
    private static final String FIND_ORDER_MESSAGE = "주문 조회";
    private static final String SAVE_ORDER_MESSAGE = "주문 저장";
    private static final String UPDATE_ORDER_MESSAGE = "주문 수정";
    private static final String CANCEL_ORDER_MESSAGE = "주문 취소";

    private final OrderService orderService;



    @GetMapping("/orders")
    public GenericResponse<List<OrderSearchResponseDto>> orderList(@RequestParam(required = false) String custName,
                                                                   @RequestParam(required = false) String orderNo,
                                                                   @RequestParam(required = false) String status,
                                                                   @RequestParam(required = false) String startDate) {

        OrderSearchRequestDto orderSearchRequestDto = new OrderSearchRequestDto(custName, orderNo, status, startDate);
        List<OrderSearchResponseDto> orders = orderService.findOrders(orderSearchRequestDto);

        return new GenericResponse<>(FIND_ORDER_MESSAGE, orders);
    }

    @PostMapping("/orders")
    public GenericResponse<OrderSaveResponseDto> saveOder(@Validated(ValidationSequence.class) @RequestBody AddOrderRequestDto addOrderRequestDto) {

        return new GenericResponse<>(
                SAVE_ORDER_MESSAGE, orderService.saveOrders(addOrderRequestDto.getOrderBasicInfo(), addOrderRequestDto.getOrderItemInfos())
        );
    }

    @PutMapping("/orders")
    public GenericResponse<OrderUpdateResponseDto> updateOrder(@Validated(ValidationSequence.class) @RequestBody UpdateOrderRequestDto updateOrderRequestDto) {

        return new GenericResponse<>(
                UPDATE_ORDER_MESSAGE, orderService.updateOrders(updateOrderRequestDto.getOrderId(), updateOrderRequestDto.getOrderBasicInfo())
        );
    }

    @PatchMapping("/orders")
    public GenericResponse<OrderCancelResponseDto> cancelOrder(@Validated(ValidationSequence.class) @RequestBody CancelOrderRequestDto cancelOrderRequestDto) {

        return new GenericResponse<>(
                CANCEL_ORDER_MESSAGE, orderService.cancelOrders(cancelOrderRequestDto.getOrderId())
        );
    }

}
