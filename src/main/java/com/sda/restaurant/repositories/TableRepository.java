package com.sda.restaurant.repositories;

import com.sda.restaurant.model.Tables;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TableRepository extends JpaRepository<Tables, Long> {

}