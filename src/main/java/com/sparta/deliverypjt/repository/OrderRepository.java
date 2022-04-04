package com.sparta.deliverypjt.repository;

import com.sparta.deliverypjt.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
