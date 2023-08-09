package com.jyj.api.order.request;

import com.jyj.api.common.validation.ValidationSequence;
import com.jyj.api.order.entity.OrderBasicInfo;
import com.jyj.api.order.entity.OrderItemInfo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.util.List;

@Getter
@Builder
@Validated(ValidationSequence.class)
public class AddOrderRequestDto {
    private Long orderId;
    @Valid
    private OrderBasicInfo orderBasicInfo;
    @Valid
    private List<OrderItemInfo> orderItemInfos;
}
