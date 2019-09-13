package com.sda.restaurant.restaurant.DTO;

import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.Set;

public class ReservationDTO {

    private Long id;
    //@DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime dateAndTime;

    private ClientDTO client;
    private Set<TablesDTO> tables;
    private Boolean occupied = true;

    public ReservationDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getDateAndTime() {
        return dateAndTime;
    }

    public void setDateAndTime(LocalDateTime dateAndTime) {
        this.dateAndTime = dateAndTime;
    }

    public ClientDTO getClient() {
        return client;
    }

    public void setClient(ClientDTO client) {
        this.client = client;
    }

    public Set<TablesDTO> getTables() {
        return tables;
    }

    public void setTables(Set<TablesDTO> tables) {
        this.tables = tables;
    }

    public Boolean getOccupied() {
        return occupied;
    }

    public void setOccupied(Boolean occupied) {
        this.occupied = occupied;
    }

    @Override
    public String toString() {
        return "ReservationDTO{" +
                "id=" + id +
                ", dateAndTime=" + dateAndTime +
                ", client=" + client +
                ", tables=" + tables +
                ", occupied=" + occupied +
                '}';
    }
}
