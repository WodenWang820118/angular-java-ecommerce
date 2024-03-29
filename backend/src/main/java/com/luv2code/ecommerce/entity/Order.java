package com.luv2code.ecommerce.entity;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="orders")
@Getter
@Setter

public class Order {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    @Column(name="order_tracking_number")
    private String orderTrackingNumber;

    @Column(name="total_price")
    private BigDecimal totalPrice;

    @Column(name="total_quantity")
    private int totalQuantity;

    @Column(name="status")
    private String status;

    @Column(name="date_created")
    @CreationTimestamp
    private Date dateCreated;

    @Column(name="last_updated")
    @UpdateTimestamp
    private Date lastUpdated;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "order")
    private Set<OrderItem> orderItems = new HashSet<>();

    // many orders to one customer
    @ManyToOne
    @JoinColumn(name="customer_id")
    private Customer customer;

    // use the foreign key and reference the id of the Address class
    @OneToOne(cascade=CascadeType.ALL)
    @JoinColumn(name="billing_address_id", referencedColumnName ="id")
    private Address shippingAddress;

    @OneToOne(cascade=CascadeType.ALL)
    @JoinColumn(name="shipping_address_id", referencedColumnName="id")
    private Address billingAddress;

    // a method to add an order item to an order
    // and mark the order as a collection of the order items
    public void add(OrderItem item) {
        if (item != null) {
            if (orderItems == null) {
                orderItems = new HashSet<>();
            }
            orderItems.add(item);
            item.setOrder(this);
        }
    }
}
