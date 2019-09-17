package com.sda.restaurant.DTO;

public class MenuDTO {

    private Long id;
    private Integer number;
    private String category;
    private String name;
    private Float price;

    public MenuDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
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

    @Override
    public String toString() {
        return "MenuDTO{" +
                "id=" + id +
                ", number=" + number +
                ", category='" + category + '\'' +
                ", name='" + name + '\'' +
                ", price=" + price +
                '}';
    }
}
