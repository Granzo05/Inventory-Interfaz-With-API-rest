package org.granzo.inventory.entities;

import jakarta.persistence.*;
@Entity
@Table(name = "products")
public class Product {
    @jakarta.persistence.Id
    @Column(name = "code")
    private String code;
    @Column(name = "name")
    private String productName;
    @Column(name = "units")
    private int units;
    @Column(name = "description")
    private String description;
    @Column(name = "price")
    private double price;
    @Column(name = "date_next_arrival", updatable = true)
    public String nextArrival;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getUnits() {
        return units;
    }

    public void setUnits(int units) {
        this.units = units;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getNextArrival() {
        return nextArrival;
    }

    public void setNextArrival(String nextArrival) {
        this.nextArrival = nextArrival;
    }

    @Override
    public String toString() {
        return "Product{" +
                "code='" + code + '\'' +
                ", productName='" + productName + '\'' +
                ", units=" + units +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", nextArrival='" + nextArrival + '\'' +
                '}';
    }
}