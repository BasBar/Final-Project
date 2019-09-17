package com.sda.restaurant.model;

import org.springframework.format.annotation.DateTimeFormat;
import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;


@Entity
public class ReservationEntity {

    @Id
    @GeneratedValue
    private Long id;

    @DateTimeFormat(pattern = "dd/MM/yyyy''hh:mm")
    private LocalDateTime dateAndTime;

    private Boolean occupied;

    @OneToOne //(cascade = CascadeType.ALL)
    private ClientEntity client;

    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.ALL})
    @JoinTable(name = "reservation_tables",
            joinColumns = {@JoinColumn(name = "reservation_id")},
            inverseJoinColumns = {@JoinColumn(name = "tables_id")})
    private Set<TablesEntity> tables = new HashSet<>();

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="reservation_id",referencedColumnName = "id")
    private OrderEntity order;


    public ReservationEntity() {
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

    public OrderEntity getOrder() {
        return order;
    }

    public void setOrder(OrderEntity order) {
        this.order = order;
    }

    public ClientEntity getClient() {
        return client;
    }

    public void setClient(ClientEntity client) {
        this.client = client;
    }

    public Boolean getOccupied() {
        return occupied;
    }

    public void setOccupied(Boolean occupied) {
        this.occupied = occupied;
    }

    public Set<TablesEntity> getTables() {
        return tables;
    }

    public void setTables(Set<TablesEntity> tables) {
        this.tables = tables;
    }
}