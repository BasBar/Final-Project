package com.sda.restaurant.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class OrderEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToOne
    private ReservationEntity reservation;

    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "order_menu",
            joinColumns = {@JoinColumn(name = "order_id")},
            inverseJoinColumns = {@JoinColumn(name = "menu_id")})
    private Set<MenuEntity> menu = new HashSet<>();

    private Double totalPrice;
    private Boolean isPaid;
    private Float tip;

    public OrderEntity() {
    }

    public OrderEntity(ReservationEntity reservation, Set<MenuEntity> menu, Double totalPrice) {
        this.reservation = reservation;
        this.menu = menu;
        this.totalPrice = totalPrice;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ReservationEntity getReservation() {
        return reservation;
    }

    public void setReservation(ReservationEntity reservation) {
        this.reservation = reservation;
    }

    public Set<MenuEntity> getMenu() {
        return menu;
    }

    public void setMenu(Set<MenuEntity> menu) {
        this.menu = menu;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Boolean getPaid() {
        return isPaid;
    }

    public void setPaid(Boolean paid) {
        isPaid = paid;
    }

    public Float getTip() {
        return tip;
    }

    public void setTip(Float tip) {
        this.tip = tip;
    }
}
