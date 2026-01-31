package com.example.ecommerce.service;

import com.example.ecommerce.util.EmailUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailService {

    private final EmailUtil emailUtil;

    public void sendWelcomeEmail(String email) {
        emailUtil.sendEmail(
                email,
                "Welcome to Ecommerce App",
                "Your account has been successfully created."
        );
    }

    public void sendOrderPlacedEmail(String email, Long orderId) {
        emailUtil.sendEmail(
                email,
                "Order Placed",
                "Your order with ID " + orderId + " has been placed successfully."
        );
    }

    public void sendPaymentSuccessEmail(String email, Long orderId) {
        emailUtil.sendEmail(
                email,
                "Payment Successful",
                "Payment successful for order ID " + orderId
        );
    }
}
