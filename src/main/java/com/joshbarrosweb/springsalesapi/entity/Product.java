package com.joshbarrosweb.springsalesapi.entity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", length = 100)
    private String name;

    @Column(name = "description", length = 255)
    private String description;

    @Column(name = "price", precision = 16, scale = 2)
    private BigDecimal price;

    @Column(name = "sku")
    private String sku;

    @Column(name = "created_at")
    private LocalDate createdAt;

    public Product() {
        super();
    }

    public Product(Long id, String name, String description, BigDecimal price, String sku) {
        super();
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.sku = sku;
    }

    @PrePersist
    public void prePersist() {
        setCreatedAt(LocalDate.now());
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public LocalDate getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDate createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", sku='" + sku + '\'' +
                '}';
    }
}
