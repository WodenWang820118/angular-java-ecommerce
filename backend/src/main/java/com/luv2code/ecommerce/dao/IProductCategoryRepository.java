package com.luv2code.ecommerce.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.luv2code.ecommerce.entity.ProductCategory;

// note the productCategory is the name of JSON entry
// and the product-category is the route path
@RepositoryRestResource(collectionResourceRel = "productCategory", path = "product-category")
public interface IProductCategoryRepository extends JpaRepository<ProductCategory, Long> {
    
}
