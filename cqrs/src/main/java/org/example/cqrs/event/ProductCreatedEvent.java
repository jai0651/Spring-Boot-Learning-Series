package org.example.cqrs.event;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ProductCreatedEvent {
    private String id;
    private String name;
    private BigDecimal price;
    private int quantity;
}