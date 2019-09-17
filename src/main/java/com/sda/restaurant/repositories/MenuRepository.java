package com.sda.restaurant.repositories;

import com.sda.restaurant.model.Menu;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MenuRepository extends JpaRepository<Menu,Long> {

    Menu findByCategory(String category);

}
