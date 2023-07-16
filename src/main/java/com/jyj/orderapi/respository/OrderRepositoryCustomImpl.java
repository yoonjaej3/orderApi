package com.jyj.orderapi.respository;

import com.jyj.orderapi.response.OrderSearchResponseDto;
import com.jyj.orderapi.response.QOrderSearchResponseDto;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import javax.persistence.EntityManager;
import java.util.List;

import static com.jyj.orderapi.entity.QOrders.orders;


@Repository
public class OrderRepositoryCustomImpl implements OrderRepositoryCustom {
    private final JPAQueryFactory queryFactory;

    public OrderRepositoryCustomImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }

    @Override
    public List<OrderSearchResponseDto> getOrderList(String custName) {

        return queryFactory.select(new QOrderSearchResponseDto(
                        orders.id
                        , orders.orderNo
                        , orders.custName
                        , orders.phoneNumber
                        , orders.address
                        , orders.status
                        , orders.orderDate

                ))
                .from(orders)
                .where(eqMember(custName))
                .fetch();

    }

    private BooleanExpression eqMember(String custName) {

        if (StringUtils.hasText(custName))
            return orders.custName.eq(custName);

        return null;
    }
}