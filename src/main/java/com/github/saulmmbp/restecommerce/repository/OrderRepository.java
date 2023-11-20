package com.github.saulmmbp.restecommerce.repository;

import com.github.saulmmbp.restecommerce.entity.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

@RepositoryRestResource
public interface OrderRepository extends JpaRepository<Order, Long> {

    @RestResource(path = "byCustomerEmail", rel = "byCustomerEmail")
    Page<Order> findByCustomerEmail(@Param("email") String email, Pageable pageable);

}
