package org.example.transaction.service;

import org.example.transaction.entity.Order;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class NotificationService {

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void sendOrderConfirmation(Order order) {
        // Simulate sending email notification
        System.out.println("Sending order confirmation for order: " + order.getId());

        // Simulate email notification failure
        // throw new RuntimeException("Email notification failed!");
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void sendFailureNotification(Order order, String reason) {
        // Simulate sending failure notification
        System.out.println("Sending failure notification for order: " + order.getId() + ". Reason: " + reason);
    }
}
