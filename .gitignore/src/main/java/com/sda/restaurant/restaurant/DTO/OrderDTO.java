package com.sda.restaurant.restaurant.DTO;

import com.sda.restaurant.restaurant.model.ReservationEntity;

import java.util.Set;

public class OrderDTO {

    private Long id;
    private ReservationEntity reservation;
    private Set<MenuDTO> menu;
    private Double totalPrice;
    private Boolean Paid = false;
    private Float tip;

    public OrderDTO() {
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

    public Set<MenuDTO> getMenu() {
        return menu;
    }

    public void setMenu(Set<MenuDTO> menu) {
        this.menu = menu;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Boolean getPaid() {
        return Paid;
    }

    public void setPaid(Boolean paid) {
        this.Paid = paid;
    }

    public Float getTip() {
        return tip;
    }

    public void setTip(Float tip) {
        this.tip = tip;
    }

    @Override
    public String toString() {
        return "OrderDTO{" +
                "id=" + id +
                ", reservation=" + reservation +
                ", menu=" + menu +
                ", totalPrice=" + totalPrice +
                ", Paid=" + Paid +
                ", tip=" + tip +
                '}';
    }
}
