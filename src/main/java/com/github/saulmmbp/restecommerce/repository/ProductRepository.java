package com.github.saulmmbp.restecommerce.repository;

import com.github.saulmmbp.restecommerce.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

@RepositoryRestResource
public interface ProductRepository extends JpaRepository<Product, Long> {

    @RestResource(path = "byCategoryId", rel = "byCategoryId")
    Page<Product> findByCategoryId(@Param("id") Long id, Pageable pageable);

    @RestResource(path = "byNameContaining", rel = "byNameContaining")
    Page<Product> findByNameContaining(@Param("name") String name, Pageable pageable);

}
