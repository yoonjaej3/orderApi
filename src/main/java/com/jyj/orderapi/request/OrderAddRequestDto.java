package com.jyj.orderapi.request;

import com.jyj.orderapi.entity.OrderBasicInfo;
import com.jyj.orderapi.entity.OrderItemInfo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderAddRequestDto {

    private OrderBasicInfo orderBasicInfo;
    private List<OrderItemInfo> orderItemInfos;

}
