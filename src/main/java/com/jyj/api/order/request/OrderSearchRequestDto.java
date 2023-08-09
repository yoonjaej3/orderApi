package com.jyj.api.order.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderSearchRequestDto {
    String custName;
    String orderNo;
    String status;
    String startDate;
}
