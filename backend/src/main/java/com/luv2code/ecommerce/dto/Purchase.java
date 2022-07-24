package com.luv2code.ecommerce.dto;

import java.util.Set;

import com.luv2code.ecommerce.entity.Address;
import com.luv2code.ecommerce.entity.Customer;
import com.luv2code.ecommerce.entity.Order;
import com.luv2code.ecommerce.entity.OrderItem;

import lombok.Data;

@Data
public class Purchase {
    // the dto means data transfer object
    // a purchase is a collection of order info
    private Customer customer;
    private Address shippingAddress;
    private Address billingAddress;
    private Order order;
    private Set<OrderItem> orderItems;
    
}
