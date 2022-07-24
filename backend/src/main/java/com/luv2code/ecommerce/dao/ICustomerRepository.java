package com.luv2code.ecommerce.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.luv2code.ecommerce.entity.Customer;

public interface ICustomerRepository extends JpaRepository<Customer, Long> {
}
