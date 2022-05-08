package com.joshbarrosweb.springsalesapi.repository.projections;

import java.math.BigDecimal;

public interface SalesByMonth {

    Integer getMonth();
    BigDecimal getValue();
}
