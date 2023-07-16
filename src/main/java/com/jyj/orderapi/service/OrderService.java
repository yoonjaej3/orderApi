package com.jyj.orderapi.service;

import com.jyj.orderapi.entity.*;
import com.jyj.orderapi.request.OrderSearchRequestDto;
import com.jyj.orderapi.response.OrderSearchResponseDto;
import com.jyj.orderapi.respository.ItemRepository;
import com.jyj.orderapi.respository.OrderItemRepository;
import com.jyj.orderapi.respository.OrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderService {

    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;
    private final ItemRepository itemRepository;

    //TODO : 주문조회
    public List<OrderSearchResponseDto> findOrders(OrderSearchRequestDto orderSearchRequestDto) {
        return orderRepository.getOrderList(orderSearchRequestDto.getCustName());
    }

    //TODO : 주문생성
    @Transactional
    public Long saveOrders(OrderBasicInfo orderBasicInfo, List<OrderItemInfo> orderItemInfos) {

        List<OrderItem> orderItems = new ArrayList<>();

        for (OrderItemInfo orderItemInfo : orderItemInfos) {

            Item item = itemRepository.getById(orderItemInfo.getItemId());
            int count = orderItemInfo.getCount();
            int totalPrice = item.getPrice() * count;

            OrderItem orderItem = OrderItem.createOrderItem(item, totalPrice, count);
            orderItems.add(orderItem);
        }

        Orders order = Orders.createOrder(orderBasicInfo, orderItems);

        orderRepository.save(order);

        return order.getId();
    }

    //TODO : 주문취소
    void cancelOrders(Orders order) {

    }

    //TODO : 주문수정
    void updateOrders(Orders order) {

    }


}
