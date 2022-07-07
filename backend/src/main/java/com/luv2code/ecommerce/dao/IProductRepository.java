package com.luv2code.ecommerce.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.luv2code.ecommerce.entity.Product;

@RepositoryRestResource(collectionResourceRel = "product", path = "products")
public interface IProductRepository extends JpaRepository<Product, Long> {
    
}
