package com.zomato.controller;

import com.zomato.dto.PaymentRequest;
import com.zomato.dto.PaymentResponse;
import com.zomato.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/payments")
@RequiredArgsConstructor
public class PaymentController {

    private final PaymentService paymentService;

    @PostMapping
    public PaymentResponse makePayment(@RequestBody PaymentRequest request) {

        return paymentService.makePayment(request);
    }

    @GetMapping("/order/{orderId}")
    public PaymentResponse getPayment(@PathVariable Long orderId) {

        return paymentService.getPaymentByOrder(orderId);
    }
}