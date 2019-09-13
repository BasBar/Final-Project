package com.sda.restaurant.restaurant.repositories;

import com.sda.restaurant.restaurant.model.TablesEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TableRepository extends JpaRepository <TablesEntity, Long> {

}