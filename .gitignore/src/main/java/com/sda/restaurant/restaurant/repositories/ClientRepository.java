package com.sda.restaurant.restaurant.repositories;

import com.sda.restaurant.restaurant.model.ClientEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<ClientEntity, Long> {

    ClientEntity findByEmail(String email);


}
