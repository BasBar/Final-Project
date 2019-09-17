package com.sda.restaurant.repositories;

import com.sda.restaurant.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, Long> {

    Client findByEmail(String email);


}
