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

    @OneToOne
    private ClientEntity client;

    @ManyToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinTable(joinColumns = {@JoinColumn},
            inverseJoinColumns = {@JoinColumn})
    private Set<TablesEntity> tables = new HashSet<>();

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="reservation_id",referencedColumnName = "id")
    private OrderEntity order;


    public ReservationEntity() {
    }

    public ReservationEntity(LocalDateTime dateAndTime, ClientEntity client, Set<TablesEntity> tables) {
        this.dateAndTime = dateAndTime;
        this.client = client;
        this.tables = tables;
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

    public Set<TablesEntity> getTables() {
        return tables;
    }

    public void setTables(Set<TablesEntity> tables) {
        this.tables = tables;
    }

}