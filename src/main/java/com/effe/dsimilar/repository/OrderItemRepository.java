package com.effe.dsimilar.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.effe.dsimilar.model.OrderItem;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {

}
