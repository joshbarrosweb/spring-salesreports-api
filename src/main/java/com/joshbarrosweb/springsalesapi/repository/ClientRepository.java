package com.joshbarrosweb.springsalesapi.repository;

import com.joshbarrosweb.springsalesapi.entity.Client;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ClientRepository extends JpaRepository<Client, Long> {

    @Query("select c from Client c where upper(c.name) like upper(:name) "
            + " and c.cpf like :cpf ")
    Page<Client> findByCpfAndName(
            @Param("name") String name,
            @Param("cpf") String cpf,
            Pageable pageable
    );
}
