package org.example.transaction.repository;

import org.example.transaction.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {
    // No need to add methods unless custom queries are required
}

