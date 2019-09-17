package com.sda.restaurant.repositories;

import com.sda.restaurant.model.MenuEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MenuRepository extends JpaRepository<MenuEntity,Long> {

    MenuEntity findByCategory(String category);

}
