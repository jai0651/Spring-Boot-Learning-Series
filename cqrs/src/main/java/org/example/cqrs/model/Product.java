package org.example.cqrs.model;


import lombok.Data;

import jakarta.persistence.*;
import org.springframework.data.elasticsearch.annotations.Document;

import java.math.BigDecimal;

@Data
@Entity
@Document(indexName = "products")  // Index name in lowercase
public class Product {
    @Id
    private String id;
    private String name;
    private BigDecimal price;
    private int quantity;
}