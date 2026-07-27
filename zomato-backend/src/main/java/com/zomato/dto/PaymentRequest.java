package com.zomato.dto;

import com.zomato.enums.PaymentMethod;
import lombok.Data;

@Data
public class PaymentRequest {

    private Long orderId;

    private PaymentMethod paymentMethod;

}