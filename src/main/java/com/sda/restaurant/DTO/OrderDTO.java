package com.sda.restaurant.DTO;

import com.sda.restaurant.model.Reservation;

import java.util.Set;

public class OrderDTO {

    private Long id;
    private Reservation reservation;
    private Set<MenuDTO> menus;
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

    public Reservation getReservation() {
        return reservation;
    }

    public void setReservation(Reservation reservation) {
        this.reservation = reservation;
    }

    public Set<MenuDTO> getMenus() {
        return menus;
    }

    public void setMenus(Set<MenuDTO> menus) {
        this.menus = menus;
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
                ", menu=" + menus +
                ", totalPrice=" + totalPrice +
                ", Paid=" + Paid +
                ", tip=" + tip +
                '}';
    }
}
