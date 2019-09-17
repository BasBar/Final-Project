package com.sda.restaurant.repositories;

import com.sda.restaurant.model.ReservationEntity;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.Optional;

public interface ReservationRepository extends JpaRepository<ReservationEntity, Long> {

    ReservationEntity getById(Long id);
    Optional<ReservationEntity> findById(Long id);

}
