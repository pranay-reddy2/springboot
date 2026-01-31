package com.example.ecommerce.controller;

import com.example.ecommerce.dto.CreatePaymentRequest;
import com.example.ecommerce.model.Payment;
import com.example.ecommerce.service.PaymentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/payments")
@RequiredArgsConstructor
public class PaymentController {

    private final PaymentService paymentService;

    @PostMapping
    public Payment create(@Valid @RequestBody CreatePaymentRequest request) {
        return paymentService.createPayment(request);
    }

    @PostMapping("/{paymentId}/success")
    public Payment success(@PathVariable Long paymentId) {
        return paymentService.success(paymentId);
    }

    @PostMapping("/{paymentId}/fail")
    public Payment fail(@PathVariable Long paymentId) {
        return paymentService.fail(paymentId);
    }
}
