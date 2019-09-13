package com.sda.restaurant.restaurant.DTO;

public class TablesDTO {

    private Long id;
    private int size;
    private Boolean occupied;

    public TablesDTO() {
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

    @Override
    public String toString() {
        return "TablesDTO{" +
                "id=" + id +
                ", size=" + size +
                '}';
    }
}
