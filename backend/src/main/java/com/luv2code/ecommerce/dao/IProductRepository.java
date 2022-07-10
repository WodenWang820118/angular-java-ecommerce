package com.luv2code.ecommerce.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestParam;

import com.luv2code.ecommerce.entity.Product;

@CrossOrigin
@RepositoryRestResource(collectionResourceRel = "product", path = "products")
public interface IProductRepository extends JpaRepository<Product, Long> {
    // the findByCategoryId method is a custom method
    // that it executes SQL query behind the scenes
    Page<Product> findByCategoryId(@RequestParam("id") Long id, Pageable pageable);
}
