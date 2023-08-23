package com.jyj.api.order.service;

import com.jyj.api.order.entity.OrderBasicInfo;
import com.jyj.api.order.entity.OrderItemInfo;
import com.jyj.api.order.request.OrderSearchRequestDto;
import com.jyj.api.order.response.OrderCancelResponseDto;
import com.jyj.api.order.response.OrderSaveResponseDto;
import com.jyj.api.order.response.OrderSearchResponseDto;
import com.jyj.api.order.response.OrderUpdateResponseDto;

import java.util.List;

public interface OrderService {
    List<OrderSearchResponseDto> findOrders(OrderSearchRequestDto orderSearchRequestDto);

    OrderSaveResponseDto saveOrders(OrderBasicInfo orderBasicInfo, List<OrderItemInfo> orderItemInfos);

    OrderCancelResponseDto cancelOrders(Long orderId);

    OrderUpdateResponseDto updateOrders(Long orderId, OrderBasicInfo orderBasicInfo);
}
