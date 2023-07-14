package com.jyj.orderapi.service;

import com.jyj.orderapi.entity.Item;
import com.jyj.orderapi.entity.OrderItem;
import com.jyj.orderapi.entity.Orders;
import com.jyj.orderapi.request.OrderAddRequestDto;
import com.jyj.orderapi.request.OrderSearchRequestDto;
import com.jyj.orderapi.response.OrderSearchResponse;
import com.jyj.orderapi.respository.ItemRepository;
import com.jyj.orderapi.respository.OrderItemRepository;
import com.jyj.orderapi.respository.OrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j

public class OrderService {

    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;
    private final ItemRepository itemRepository;

    //TODO : 주문조회
    List<OrderSearchResponse> getOrders(OrderSearchRequestDto orderSearchRequest) {
        return null;
    }

    //TODO : 주문생성
    @Transactional
    public Long saveOrders(OrderAddRequestDto orderAddRequestDto) {

        Orders order = Orders.builder()
                .custName(orderAddRequestDto.getCustName())
                .phoneNumber(orderAddRequestDto.getPhoneNumber())
                .address(orderAddRequestDto.getAddress())
                .build();

        order.makeOrderNo();
        order.basicStatus();

        orderRepository.save(order);


        Item item = itemRepository.getById(orderAddRequestDto.getItemId());

        OrderItem orderItem = OrderItem.builder()
                .item(item)
                .order(order)
                .orderPrice(item.getPrice() * orderAddRequestDto.getCount())
                .count(orderAddRequestDto.getCount())
                .build();

        return order.getId();

    }

    //TODO : 주문취소
    void cancelOrders(Orders order) {

    }

    //TODO : 주문수정
    void updateOrders(Orders order) {

    }


}
