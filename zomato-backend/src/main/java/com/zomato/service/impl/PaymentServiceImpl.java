package com.zomato.service.impl;

import com.zomato.dto.PaymentRequest;
import com.zomato.dto.PaymentResponse;
import com.zomato.entity.Order;
import com.zomato.entity.Payment;
import com.zomato.enums.PaymentStatus;
import com.zomato.repository.OrderRepository;
import com.zomato.repository.PaymentRepository;
import com.zomato.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {

    private final PaymentRepository paymentRepository;
    private final OrderRepository orderRepository;

    @Override
    public PaymentResponse makePayment(PaymentRequest request) {

        Order order = orderRepository.findById(request.getOrderId())
                .orElseThrow(() -> new RuntimeException("Order not found"));

        paymentRepository.findByOrderId(order.getId())
                .ifPresent(payment -> {
                    throw new RuntimeException("Payment already exists for this order");
                });

        Payment payment = Payment.builder()
                .order(order)
                .amount(order.getTotalAmount())
                .paymentMethod(request.getPaymentMethod())
                .paymentStatus(PaymentStatus.SUCCESS)
                .transactionId(UUID.randomUUID().toString())
                .build();

        return map(paymentRepository.save(payment));
    }

    @Override
    public PaymentResponse getPaymentByOrder(Long orderId) {

        Payment payment = paymentRepository.findByOrderId(orderId)
                .orElseThrow(() -> new RuntimeException("Payment not found"));

        return map(payment);
    }

    private PaymentResponse map(Payment payment) {

        return PaymentResponse.builder()
                .paymentId(payment.getId())
                .orderId(payment.getOrder().getId())
                .amount(payment.getAmount())
                .paymentMethod(payment.getPaymentMethod())
                .paymentStatus(payment.getPaymentStatus())
                .transactionId(payment.getTransactionId())
                .paymentDate(payment.getPaymentDate())
                .build();
    }
}