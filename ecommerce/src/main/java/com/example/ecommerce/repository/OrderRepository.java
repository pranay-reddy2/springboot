package com.example.ecommerce.repository;

import com.example.ecommerce.model.Order;
import com.example.ecommerce.model.OrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {

    /**
     * Fetch all orders of a specific user
     */
    List<Order> findByUserId(Long userId);

    /**
     * Count orders by status (for analytics)
     */
    long countByStatus(OrderStatus status);

    /**
     * Calculate total revenue from PAID orders
     */
    @Query("SELECT COALESCE(SUM(o.totalAmount), 0) FROM Order o WHERE o.status = 'PAID'")
    Double getTotalRevenue();
}
