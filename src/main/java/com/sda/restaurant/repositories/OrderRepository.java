package com.sda.restaurant.repositories;

import com.sda.restaurant.model.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<OrderEntity, Long> {

    OrderEntity getById(Long id);

}
