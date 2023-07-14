package com.jyj.orderapi.respository;

import com.jyj.orderapi.entity.Item;
import com.jyj.orderapi.entity.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item, Long> {

}
