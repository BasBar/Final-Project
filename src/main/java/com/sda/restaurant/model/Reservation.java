package com.sda.restaurant.model;

import org.springframework.format.annotation.DateTimeFormat;
import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;


@Entity
@Table(name = "reservation_entity")
public class Reservation {

    @Id
    @GeneratedValue
    private Long id;

    @DateTimeFormat(pattern = "dd/MM/yyyy''hh:mm")
    private LocalDateTime dateAndTime;

    @OneToOne
    private Client client;

    @ManyToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinTable(joinColumns = {@JoinColumn},
            inverseJoinColumns = {@JoinColumn})
    private Set<Tables> tables = new HashSet<>();

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="reservation_id",referencedColumnName = "id")
    private Order order;


    public Reservation() {
    }

    public Reservation(LocalDateTime dateAndTime, Client client, Set<Tables> tables) {
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

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Set<Tables> getTables() {
        return tables;
    }

    public void setTables(Set<Tables> tables) {
        this.tables = tables;
    }

}