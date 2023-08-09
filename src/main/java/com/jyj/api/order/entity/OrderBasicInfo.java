package com.jyj.api.order.entity;

import com.jyj.api.common.validation.group.NotEmptyGroup;
import lombok.*;

import javax.validation.constraints.NotEmpty;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class OrderBasicInfo {
    @NotEmpty(groups = {NotEmptyGroup.class})
    private String address;

    @NotEmpty(groups = {NotEmptyGroup.class})
    private String phoneNumber;

    @NotEmpty(groups = {NotEmptyGroup.class})
    private String custName;
}