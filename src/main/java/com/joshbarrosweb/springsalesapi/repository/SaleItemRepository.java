package com.joshbarrosweb.springsalesapi.repository;

import com.joshbarrosweb.springsalesapi.entity.SaleItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SaleItemRepository extends JpaRepository<SaleItem, Long> {
}
