package com.jyj.orderapi.respository;

import com.jyj.orderapi.entity.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Orders, Long>, OrderRepositoryCustom {
    List<Orders> findByCustName(String custName);
}
