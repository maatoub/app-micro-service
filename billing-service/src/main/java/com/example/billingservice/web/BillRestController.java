package com.example.billingservice.web;

import com.example.billingservice.entities.Bill;
import com.example.billingservice.repository.BillRepository;
import com.example.billingservice.repository.ProductItemRepository;
import com.example.billingservice.services.CustomerRestClient;
import com.example.billingservice.services.ProductRestClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController

public class BillRestController {

    private BillRepository billRepository;

    private ProductItemRepository productItemRepository;

    private CustomerRestClient customerRestClient;

    private ProductRestClient productRestClient;

    public BillRestController(BillRepository billRepository,
                              ProductItemRepository productItemRepository,
                              CustomerRestClient customerRestClient,
                              ProductRestClient productRestClient) {
        this.billRepository = billRepository;
        this.productItemRepository = productItemRepository;
        this.customerRestClient = customerRestClient;
        this.productRestClient = productRestClient;
    }

    @GetMapping("/Bills/{id}")
    public Bill bill(@PathVariable Long id){
        Bill bill=billRepository.findById(id).get();
        bill.setCustomer(customerRestClient.findCustomerById(bill.getCustomerId()));
        bill.getProductItems().forEach(product->{
            product.setProduct(productRestClient.findProductById(product.getProductId()));
        });
        return bill;
    }
}
