package com.example.ecommerce.service;

import com.example.ecommerce.dto.OrderRequest;
import com.example.ecommerce.exception.ResourceNotFoundException;
import com.example.ecommerce.model.Order;
import com.example.ecommerce.model.OrderStatus;
import com.example.ecommerce.model.User;
import com.example.ecommerce.repository.OrderRepository;
import com.example.ecommerce.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final UserRepository userRepository;
    private final EmailService emailService;

    /**
     * Create a new order
     */
    public Order create(OrderRequest request) {

        // Validate user
        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        Order order = new Order();
        order.setUserId(user.getId());
        order.setTotalAmount(request.getTotalAmount());
        order.setStatus(OrderStatus.CREATED);

        Order savedOrder = orderRepository.save(order);

        // Send order placed email
        emailService.sendOrderPlacedEmail(user.getEmail(), savedOrder.getId());

        return savedOrder;
    }

    /**
     * Get order by ID
     */
    public Order getById(Long orderId) {
        return orderRepository.findById(orderId)
                .orElseThrow(() -> new ResourceNotFoundException("Order not found"));
    }

    /**
     * Get all orders of a user
     */
    public List<Order> getByUser(Long userId) {

        // Optional: validate user exists
        if (!userRepository.existsById(userId)) {
            throw new ResourceNotFoundException("User not found");
        }

        return orderRepository.findByUserId(userId);
    }

    /**
     * Update order status (ADMIN / SYSTEM use)
     */
    public Order updateStatus(Long orderId, OrderStatus status) {

        Order order = getById(orderId);
        order.setStatus(status);

        return orderRepository.save(order);
    }
}
