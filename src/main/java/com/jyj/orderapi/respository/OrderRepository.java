package com.jyj.orderapi.respository;

import com.jyj.orderapi.entity.Orders;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository  extends JpaRepository<Orders,Long>, OrderRepositoryCustom {

}
