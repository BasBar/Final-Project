package com.sda.restaurant.repositories;

import com.sda.restaurant.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {

    Order getById(Long id);
}
