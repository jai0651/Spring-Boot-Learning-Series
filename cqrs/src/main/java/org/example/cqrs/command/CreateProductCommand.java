package org.example.cqrs.command;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class CreateProductCommand {
    private String name;
    private BigDecimal price;
    private int quantity;
}