package com.jyj.api.order.respository;

import com.jyj.api.order.response.OrderSearchResponseDto;

import java.util.List;

public interface OrderRepositoryCustom {
    List<OrderSearchResponseDto> getOrderList(String orderNo, String custName, String status, String startDate);
}
