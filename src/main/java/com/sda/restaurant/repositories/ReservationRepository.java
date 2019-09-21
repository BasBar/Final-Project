package com.sda.restaurant.repositories;

import com.sda.restaurant.model.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.Optional;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {

    Reservation getById(Long id);
    Optional<Reservation> findById(Long id);
}
