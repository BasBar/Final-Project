package com.sda.restaurant.model;


import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * This class contains information about the menu items in the restaurant
 */
@Entity
public class MenuEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Integer number;
    private String category;
    private String name;
    private Float price;

    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST,CascadeType.MERGE}, mappedBy = "menu")
    private Set<OrderEntity> orderEntity = new HashSet<>();

    public MenuEntity() {
    }

    public MenuEntity(Integer number, String category, String name, Float price) {
        this.number = number;
        this.category = category;
        this.name = name;
        this.price = price;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public Set<OrderEntity> getOrderEntity() {
        return orderEntity;
    }

    public void setOrderEntity(Set<OrderEntity> orderEntity) {
        this.orderEntity = orderEntity;
    }

    @Override
    public String toString() {
        return "MenuEntity{" +
                "id=" + id +
                ", category='" + category + '\'' +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", Number=" + number +
                '}';
    }
}
