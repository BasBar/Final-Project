package com.sda.restaurant.restaurant.repositories;

import com.sda.restaurant.restaurant.model.MenuEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MenuRepository extends JpaRepository <MenuEntity,Long> {

    MenuEntity findByCategory(String category);

}
