package com.sda.restaurant.repositories;

import com.sda.restaurant.model.ClientEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<ClientEntity, Long> {

    ClientEntity findByEmail(String email);


}
