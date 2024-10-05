package org.example.cqrs.service;


import org.example.cqrs.command.CreateProductCommand;
import org.example.cqrs.event.ProductCreatedEvent;
import org.example.cqrs.model.Product;
import org.example.cqrs.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ProductCommandService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private KafkaTemplate<String, ProductCreatedEvent> kafkaTemplate;

    public String createProduct(CreateProductCommand command) {
        Product product = new Product();
        product.setId(UUID.randomUUID().toString());
        product.setName(command.getName());
        product.setPrice(command.getPrice());
        product.setQuantity(command.getQuantity());

        productRepository.save(product);

        ProductCreatedEvent event = new ProductCreatedEvent();
        event.setId(product.getId());
        event.setName(product.getName());
        event.setPrice(product.getPrice());
        event.setQuantity(product.getQuantity());

        kafkaTemplate.send("product-created", event);

        return product.getId();
    }
}