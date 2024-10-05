package org.example.transaction.service;

import org.example.transaction.entity.Order;
import org.example.transaction.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class OrderService {

    @Autowired
    private InventoryService inventoryService;

    @Autowired
    private PaymentService paymentService;

    @Autowired
    private ShippingService shippingService;

    @Autowired
    private NotificationService notificationService;

    @Autowired
    private OrderRepository orderRepository;

    @Transactional
    public void processOrder(Order order) {
        try {
            // Step 1: Reserve inventory for the order
            inventoryService.reserveInventory(order.getProductList());

            // Step 2: Process payment
            paymentService.processPayment(order.getPayment());

            // Step 3: Create shipping request
            shippingService.createShippingRequest(order);

            // Step 4: Notify customer
            notificationService.sendOrderConfirmation(order);

            // Step 5: Save order in the database
            saveOrder(order);

        } catch (Exception e) {
            // Rollback transaction and release inventory
            System.out.println("Order processing failed: " + e.getMessage());
            inventoryService.releaseInventory(order.getProductList());

            // Notify customer of failure
            notificationService.sendFailureNotification(order, e.getMessage());
            throw e;  // Re-throw exception to ensure rollback
        }
    }

    // Retrieve all orders
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    // Retrieve order by ID
    @Transactional
    public Optional<Order> getOrderById(Long id) {
        return orderRepository.findById(id);
    }

    // Save a new order
    public Order saveOrder(Order order) {
        processOrder(order);
        return orderRepository.save(order);
    }

    // Update an existing order by ID
    public Optional<Order> updateOrder(Long id, Order updatedOrder) {
        return orderRepository.findById(id).map(existingOrder -> {
            existingOrder.setProductList(updatedOrder.getProductList());
            existingOrder.setPayment(updatedOrder.getPayment());
            existingOrder.setShippingDetails(updatedOrder.getShippingDetails());
            return orderRepository.save(existingOrder);
        });
    }

    // Delete an order by ID
    public boolean deleteOrder(Long id) {
        return orderRepository.findById(id).map(order -> {
            orderRepository.delete(order);
            return true;
        }).orElse(false);
    }
}
