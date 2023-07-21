package com.jyj.api.order.respository;

import com.jyj.api.order.entity.enums.OrderStatus;
import com.jyj.api.order.response.OrderSearchResponseDto;
import com.jyj.api.order.response.QOrderSearchResponseDto;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import javax.persistence.EntityManager;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static com.jyj.api.order.entity.QItem.item;
import static com.jyj.api.order.entity.QOrderItem.orderItem;
import static com.jyj.api.order.entity.QOrders.orders;


@Repository
public class OrderRepositoryCustomImpl implements OrderRepositoryCustom {
    private final JPAQueryFactory queryFactory;

    public OrderRepositoryCustomImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }

    @Override
    public List<OrderSearchResponseDto> getOrderList(String orderNo, String custName, String status, String startDate) {

        return queryFactory.select(new QOrderSearchResponseDto(
                        orders.id
                        , orders.orderNo
                        , orders.custName
                        , orders.phoneNumber
                        , orders.address
                        , orders.status
                        , orders.orderDate
                        , item.name
                        , orderItem.orderPrice
                        , orderItem.count

                ))
                .from(orders)
                .join(orders.orderItems, orderItem)
                .join(orderItem.item, item)
                .where(eqOrderNo(orderNo), eqCustName(custName), eqStatus(status), goeDate(startDate))
                .fetch();

    }

    private BooleanExpression eqOrderNo(String orderNo) {
        if (StringUtils.isEmpty(orderNo)) {
            return null;
        }
        return orders.orderNo.eq(orderNo);
    }

    private BooleanExpression eqCustName(String custName) {
        if (StringUtils.isEmpty(custName)) {
            return null;
        }
        return orders.custName.eq(custName);
    }

    private BooleanExpression eqStatus(String status) {
        if (StringUtils.isEmpty(status)) {
            return null;
        }
        return orders.status.eq(OrderStatus.valueOf(status));
    }

    private BooleanExpression goeDate(String startDate) {
        if (StringUtils.isEmpty(startDate)) {
            return null;
        }

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDateTime startDateTime = LocalDate.parse(startDate, formatter).atStartOfDay();

        return orders.orderDate.goe(startDateTime);
    }

}