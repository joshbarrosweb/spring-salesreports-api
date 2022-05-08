package com.joshbarrosweb.springsalesapi.controller;

import com.joshbarrosweb.springsalesapi.data.DashboardData;
import com.joshbarrosweb.springsalesapi.repository.ClientRepository;
import com.joshbarrosweb.springsalesapi.repository.ProductRepository;
import com.joshbarrosweb.springsalesapi.repository.SaleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@RestController
@RequestMapping("/api/dashboard")
public class DashboardController {

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private SaleRepository saleRepository;

    @GetMapping
    public DashboardData getDashboard() {
        long clientsCount = clientRepository.count();
        long productsCount = productRepository.count();
        long salesCount = saleRepository.count();

        var salesByMonth = saleRepository.getSalesByMonthSum(LocalDate.now().getYear());

        return new DashboardData(clientsCount, productsCount, salesCount, salesByMonth);
    }
}
