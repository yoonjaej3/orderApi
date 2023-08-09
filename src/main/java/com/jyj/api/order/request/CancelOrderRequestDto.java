package com.jyj.api.order.request;

import com.jyj.api.common.validation.ValidationSequence;
import com.jyj.api.common.validation.group.NotEmptyGroup;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Validated(ValidationSequence.class)
public class CancelOrderRequestDto {
    @NotNull(groups = {NotEmptyGroup.class})
    private Long orderId;
}
