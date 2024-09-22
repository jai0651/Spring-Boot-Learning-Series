package org.example.transaction.service;

import org.example.transaction.entity.Product;
import org.example.transaction.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class InventoryService {

    @Autowired
    private ProductRepository productRepository;

    @Transactional
    public void reserveInventory(List<Product> productList) {
        for (Product product : productList) {
            Product dbProduct = productRepository.findById(product.getId())
                    .orElseThrow(() -> new RuntimeException("Product not found"));

            if (dbProduct.getStock() < product.getQuantity()) {
                throw new RuntimeException("Insufficient stock for product: " + product.getName());
            }

            dbProduct.setStock(dbProduct.getStock() - product.getQuantity());
            productRepository.save(dbProduct);

            // Log inventory action (this can fail independently)
            logInventoryAction(product);
        }
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void logInventoryAction(Product product) {
        // Assume this logs inventory actions in a separate DB table
        System.out.println("Logging inventory action for product: " + product.getName());
        // Simulate exception
        // throw new RuntimeException("Inventory log failed!");
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void releaseInventory(List<Product> productList) {
        // Rollback stock if the order fails
        for (Product product : productList) {
            Product dbProduct = productRepository.findById(product.getId())
                    .orElseThrow(() -> new RuntimeException("Product not found"));

            dbProduct.setStock(dbProduct.getStock() + product.getQuantity());
            productRepository.save(dbProduct);
        }
    }
}
