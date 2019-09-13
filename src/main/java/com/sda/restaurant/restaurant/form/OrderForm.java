package com.sda.restaurant.restaurant.form;

public class OrderForm {

    private Long reservationId;
    private Long[] menuIds;
    private Boolean isPaid = false;
    private Float tip;

    public OrderForm() {}

    public OrderForm(Long[] menuIds) {
        this.menuIds = menuIds;
    }

    public Long getReservationId() {
        return reservationId;
    }

    public void setReservationId(Long reservationId) {
        this.reservationId = reservationId;
    }

    public Long[] getMenuIds() {
        return menuIds;
    }

    public void setMenuIds(Long[] menuIds) {
        this.menuIds = menuIds;
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
