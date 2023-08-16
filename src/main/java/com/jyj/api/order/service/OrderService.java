package com.jyj.api.order.service;

import com.jyj.api.order.entity.*;
import com.jyj.api.order.request.OrderSearchRequestDto;
import com.jyj.api.order.response.OrderSearchResponseDto;
import com.jyj.api.order.respository.ItemRepository;
import com.jyj.api.order.respository.OrderItemRepository;
import com.jyj.api.order.respository.OrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderService {

    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;
    private final ItemRepository itemRepository;

    public List<OrderSearchResponseDto> findOrders(OrderSearchRequestDto orderSearchRequestDto) {
        log.debug("Find orders: {}", orderSearchRequestDto);

        List<OrderSearchResponseDto> orderList = orderRepository.getOrderList(orderSearchRequestDto.getOrderNo(), orderSearchRequestDto.getCustName()
                , orderSearchRequestDto.getStatus(), orderSearchRequestDto.getStartDate());

        return orderList;
    }

    @Transactional
    public Long saveOrders(OrderBasicInfo orderBasicInfo, List<OrderItemInfo> orderItemInfos) {
        log.debug("Save orders: {}, {}", orderBasicInfo, orderItemInfos);

        List<OrderItem> orderItems = createOrderItems(orderItemInfos);
        Orders order = Orders.createOrder(orderBasicInfo, orderItems);
        orderRepository.save(order);

        int custNameCount = orderRepository.findByCustName(order.getCustName()).size();
        order.makeOrderNo(custNameCount);

        return order.getId();
    }

    @Transactional
    public Long cancelOrders(Long orderId) {
        log.debug("Cancel orderId: {}", orderId);

        Orders order = orderRepository.findById(orderId)
                .orElseThrow(() -> new EntityNotFoundException("OrderId " + orderId + " not found"));
        order.cancel(order);

        return order.getId();
    }

    @Transactional
    public Long updateOrders(Long orderId, OrderBasicInfo orderBasicInfo) {
        log.debug("Update orderId: {}", orderId);

        Orders order = orderRepository.findById(orderId)
                .orElseThrow(() -> new EntityNotFoundException("OrderId " + orderId + " not found"));
        order.updateOrder(orderBasicInfo);

        return order.getId();
    }

    private List<OrderItem> createOrderItems(List<OrderItemInfo> orderItemInfos) {
        List<OrderItem> orderItems = new ArrayList<>();

        for (OrderItemInfo orderItemInfo : orderItemInfos) {
            Item item = itemRepository.getById(orderItemInfo.getItemId());
            int count = orderItemInfo.getCount();
            int totalPrice = item.getPrice() * count;

            OrderItem orderItem = OrderItem.createOrderItem(item, totalPrice, count);
            orderItems.add(orderItem);
        }
        return orderItems;
    }


}
