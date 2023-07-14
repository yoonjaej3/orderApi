package com.jyj.orderapi.respository;

import com.jyj.orderapi.entity.OrderItem;
import com.jyj.orderapi.entity.Orders;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {

}
