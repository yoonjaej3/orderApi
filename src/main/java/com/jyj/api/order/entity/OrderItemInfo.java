package com.jyj.api.order.entity;

import com.jyj.api.common.validation.group.MinMaxGroup;
import com.jyj.api.common.validation.group.NotEmptyGroup;
import lombok.*;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class OrderItemInfo {
    @NotNull(groups = {NotEmptyGroup.class})
    private Long itemId;

    @Min(value = 1, groups = {MinMaxGroup.class})
    @Max(value = 99999, groups = {MinMaxGroup.class})
    private int count;
}
