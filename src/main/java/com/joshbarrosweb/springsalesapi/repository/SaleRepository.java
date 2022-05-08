package com.joshbarrosweb.springsalesapi.repository;

import com.joshbarrosweb.springsalesapi.entity.Sale;
import com.joshbarrosweb.springsalesapi.repository.projections.SalesByMonth;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SaleRepository extends JpaRepository<Sale, Long> {
    @Query(nativeQuery = true,
            value = "select "
            + " extract( month from s.created_at ) as month, "
            + " sum( s.total ) as value"
            + " from sale as s"
            + " where extract ( year from s.created_at ) = :year "
            + " group by extract( month from s.created_at )"
            + " order by extract( month from s.created_at )"
    )
    List<SalesByMonth> getSalesByMonthSum(@Param("year") Integer year);
}
