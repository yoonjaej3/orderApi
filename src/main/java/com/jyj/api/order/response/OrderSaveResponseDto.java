package com.jyj.api.order.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.jyj.api.order.entity.enums.OrderStatus;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Builder;
import lombok.Getter;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.time.LocalDateTime;

@Getter
@Builder
public class OrderSaveResponseDto {
    private final Long orderid;

}
