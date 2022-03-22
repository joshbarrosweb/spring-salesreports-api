package com.joshbarrosweb.springsalesapi.formrequest;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.joshbarrosweb.springsalesapi.entity.Product;

import java.math.BigDecimal;
import java.time.LocalDate;

public class ProductFormRequest {

    private Long id;
    private String name;
    private String description;
    private String sku;
    private BigDecimal price;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate submission;

    public ProductFormRequest() {
        super();
    }

    public ProductFormRequest(Long id, String name, String description, String sku, BigDecimal price, LocalDate submission) {
        super();
        this.id = id;
        this.name = name;
        this.description = description;
        this.sku = sku;
        this.price = price;
        this.submission = submission;
    }

    public Product toModel() {
        return new Product(id, name, description, price, sku);
    }

    public static ProductFormRequest fromModel(Product product) {

        return new ProductFormRequest(
                product.getId(),
                product.getName(),
                product.getDescription(),
                product.getSku(),
                product.getPrice(),
                product.getCreatedAt()
        );

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

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public LocalDate getSubmission() {
        return submission;
    }

    public void setSubmission(LocalDate submission) {
        this.submission = submission;
    }

    @Override
    public String toString() {
        return "ProductFormRequest{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", sku='" + sku + '\'' +
                ", price=" + price +
                '}';
    }
}
