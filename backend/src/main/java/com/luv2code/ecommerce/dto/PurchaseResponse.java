package com.luv2code.ecommerce.dto;

import lombok.Data;

@Data
public class PurchaseResponse {
    // when purchase is successful, the response will generate a UUID version 4 string
    private final String orderTrackingNumber;
}
