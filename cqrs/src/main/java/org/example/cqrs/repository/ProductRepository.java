package org.example.cqrs.repository;


import org.example.cqrs.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("jpaProductRepository")
public interface ProductRepository extends JpaRepository<Product, String> {
}