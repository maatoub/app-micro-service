package com.example.billingservice.services;

import com.example.billingservice.models.Product;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.hateoas.PagedModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "INVENTORY-SERVICE")
public interface ProductRestClient {

    @GetMapping("products/{id}")
    Product findProductById(@PathVariable Long id);

    @GetMapping("/products")
    PagedModel<Product> allProducts();
}
