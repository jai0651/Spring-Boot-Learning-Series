package org.example.transaction.service;

import org.example.transaction.entity.Payment;
import org.example.transaction.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class PaymentService {

    @Autowired
    private PaymentRepository paymentRepository;

    @Transactional
    public void processPayment(Payment payment) {
        // Simulate payment processing
        System.out.println("Processing payment of amount: " + payment.getAmount());

        // If payment fails, it will roll back the entire transaction
        if (payment.getAmount() <= 0) {
            throw new RuntimeException("Payment failed!");
        }

        // Log payment transaction separately
        logPaymentTransaction(payment);

        // Save payment to database after successful processing
        savePayment(payment);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void logPaymentTransaction(Payment payment) {
        // Simulate logging the payment transaction
        System.out.println("Logging payment of amount: " + payment.getAmount());
    }

    // Retrieve all payments
    public List<Payment> getAllPayments() {
        return paymentRepository.findAll();
    }

    // Retrieve payment by ID
    public Optional<Payment> getPaymentById(Long id) {
        return paymentRepository.findById(id);
    }

    // Save a new payment
    public Payment savePayment(Payment payment) {
        return paymentRepository.save(payment);
    }

    // Update an existing payment by ID
    public Optional<Payment> updatePayment(Long id, Payment updatedPayment) {
        return paymentRepository.findById(id).map(existingPayment -> {
            existingPayment.setAmount(updatedPayment.getAmount());
            return paymentRepository.save(existingPayment);
        });
    }

    // Delete a payment by ID
    public boolean deletePayment(Long id) {
        return paymentRepository.findById(id).map(payment -> {
            paymentRepository.delete(payment);
            return true;
        }).orElse(false);
    }
}
