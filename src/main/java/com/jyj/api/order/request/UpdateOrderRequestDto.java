package com.jyj.api.order.request;

import com.jyj.api.common.validation.ValidationSequence;
import com.jyj.api.common.validation.group.NotEmptyGroup;
import com.jyj.api.order.entity.OrderBasicInfo;
import lombok.Builder;
import lombok.Getter;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Getter
@Builder
@Validated(ValidationSequence.class)
public class UpdateOrderRequestDto {
    @NotNull(groups = {NotEmptyGroup.class})
    private Long orderId;

    @Valid
    private OrderBasicInfo orderBasicInfo;
}
