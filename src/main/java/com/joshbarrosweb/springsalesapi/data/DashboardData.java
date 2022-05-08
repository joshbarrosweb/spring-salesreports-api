package com.joshbarrosweb.springsalesapi.data;

import com.joshbarrosweb.springsalesapi.repository.projections.SalesByMonth;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class DashboardData {

    private Long products;
    private Long clients;
    private Long sales;
    private List<SalesByMonth> salesByMonth;

    public DashboardData(Long products, Long clients, Long sales, List<SalesByMonth> salesByMonth) {
        this.products = products;
        this.clients = clients;
        this.sales = sales;
        this.salesByMonth = salesByMonth;
        this.fillMissingMonths();
    }

    public Long getProducts() {
        return products;
    }

    public void setProducts(Long products) {
        this.products = products;
    }

    public Long getClients() {
        return clients;
    }

    public void setClients(Long clients) {
        this.clients = clients;
    }

    public Long getSales() {
        return sales;
    }

    public void setSales(Long sales) {
        this.sales = sales;
    }

    public List<SalesByMonth> getSalesByMonth() {
        if(salesByMonth == null) {
            salesByMonth = new ArrayList<>();
        }
        return salesByMonth;
    }

    public void setSalesByMonth(List<SalesByMonth> salesByMonth) {
        this.salesByMonth = salesByMonth;
    }

    public void fillMissingMonths() {
        if(getSalesByMonth().isEmpty()) {
            return;
        }

        Integer maxMonth = getSalesByMonth()
                                .stream()
                                .mapToInt(SalesByMonth::getMonth)
                                .max()
                                .getAsInt();

        List<Integer> monthsList = IntStream.rangeClosed(1, maxMonth).boxed().collect(Collectors.toList());

        List<Integer> addedMonths = getSalesByMonth().stream().map(SalesByMonth::getMonth).collect(Collectors.toList());

        monthsList.stream().forEach(month -> {
            if(!addedMonths.contains(month)) {
                SalesByMonth salesByMonth = new SalesByMonth() {
                    @Override
                    public Integer getMonth() {
                        return month;
                    }

                    @Override
                    public BigDecimal getValue() {
                        return BigDecimal.ZERO;
                    }
                };
                getSalesByMonth().add(null);
            }
        });

        getSalesByMonth().sort(Comparator.comparing(SalesByMonth::getMonth));
    }
}
