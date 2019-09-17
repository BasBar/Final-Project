package com.sda.restaurant.repositories;

import com.sda.restaurant.model.TablesEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TableRepository extends JpaRepository<TablesEntity, Long> {

}