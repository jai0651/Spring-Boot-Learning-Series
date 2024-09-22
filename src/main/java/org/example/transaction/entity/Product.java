package org.example.transaction.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "quantity")
    private int quantity;

    @Column(name = "stock")
    private int stock;

    @Column(name = "price" ,nullable = false)
    private int price;

    @Column(name = "description")
    private String description;  // Fixed the variable name

}
