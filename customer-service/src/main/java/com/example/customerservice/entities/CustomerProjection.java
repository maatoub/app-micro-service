package com.example.customerservice.entities;

import org.springframework.data.rest.core.config.Projection;

// api?projection=fullCustomer
@Projection(name = "fullCustomer", types = Customer.class)
public interface CustomerProjection {
    public Long getId();
    public String getName();
}
