package com.jyj.orderapi.respository;

import com.jyj.orderapi.request.OrderSearchRequestDto;
import com.jyj.orderapi.response.OrderSearchResponse;

import java.util.List;

public interface OrderRepositoryCustom {
    List<OrderSearchResponse> getOrderList(OrderSearchRequestDto orderSearchRequest);
}
