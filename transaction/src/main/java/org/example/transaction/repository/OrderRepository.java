package org.example.transaction.repository;

import org.example.transaction.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    // No need to add methods unless custom queries are required
}
