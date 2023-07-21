package com.jyj.api.order.request;

import com.jyj.api.order.entity.OrderBasicInfo;
import com.jyj.api.order.entity.OrderItemInfo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderRequestDto {

    private OrderBasicInfo orderBasicInfo;
    private List<OrderItemInfo> orderItemInfos;

}
