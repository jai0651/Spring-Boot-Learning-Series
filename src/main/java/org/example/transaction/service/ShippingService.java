package org.example.transaction.service;

import org.example.transaction.entity.Order;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ShippingService {

    @Transactional
    public void createShippingRequest(Order order) {
        // Simulate creating a shipping request
        System.out.println("Creating shipping request for order: " + order.getId());

        // Simulate shipping failure
        // throw new RuntimeException("Shipping request failed!");
    }
}
