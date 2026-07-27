package com.zomato.service;

import com.zomato.dto.PaymentRequest;
import com.zomato.dto.PaymentResponse;

public interface PaymentService {

    PaymentResponse makePayment(PaymentRequest request);

    PaymentResponse getPaymentByOrder(Long orderId);

}