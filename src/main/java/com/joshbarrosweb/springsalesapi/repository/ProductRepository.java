package com.joshbarrosweb.springsalesapi.repository;

import com.joshbarrosweb.springsalesapi.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
