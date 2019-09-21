package com.sda.restaurant.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "table_entity")
public class Tables {

    @Id
    @GeneratedValue
    private Long id;
    private int size;
    private Boolean occupied;

    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE}, mappedBy = "tables")
    private Set<Reservation> reservationEntity = new HashSet<>();

    public Tables() {
    }

    public Tables(int size) {

        this.size = size;
        this.occupied = Boolean.FALSE;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public Boolean getOccupied() {
        return occupied;
    }

    public void setOccupied(Boolean occupied) {
        this.occupied = occupied;
    }

    public Set<Reservation> getReservationEntity() {
        return reservationEntity;
    }

    public void setReservationEntity(Set<Reservation> reservation) {
        this.reservationEntity = reservation;
    }
}
