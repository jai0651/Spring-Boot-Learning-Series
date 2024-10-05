package org.example.transaction.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Many orders can have multiple products, so use @JoinColumn to define the foreign key column for the relationship.
    @OneToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinColumn(name = "order_id") // This is necessary to create the foreign key in the products table.
    private List<Product> productList;

    // Each order has one payment
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "payment_id") // Defines the foreign key column for payment
    private Payment payment;

    // No need to annotate non-entity fields with @Column, unless specific constraints are required.
    private String shippingDetails;
}
