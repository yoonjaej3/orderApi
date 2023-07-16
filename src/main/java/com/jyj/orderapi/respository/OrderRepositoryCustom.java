package com.jyj.orderapi.respository;

import com.jyj.orderapi.request.OrderSearchRequestDto;
import com.jyj.orderapi.response.OrderSearchResponseDto;

import java.util.List;

public interface OrderRepositoryCustom {
    List<OrderSearchResponseDto> getOrderList(String custName);
}
