package com.jyj.orderapi.respository;

import com.jyj.orderapi.request.OrderSearchRequestDto;
import com.jyj.orderapi.response.OrderSearchResponse;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
public class OrderRepositoryCustomImpl implements  OrderRepositoryCustom{
    private final JPAQueryFactory queryFactory;

    public OrderRepositoryCustomImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }

    @Override
    public List<OrderSearchResponse> getOrderList(OrderSearchRequestDto orderSearchRequest) {
        //return queryFactory.select(new QOrder)

        return null;
    }
}