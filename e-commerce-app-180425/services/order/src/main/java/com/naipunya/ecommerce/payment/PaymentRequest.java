package com.naipunya.ecommerce.payment;

import com.naipunya.ecommerce.customer.CustomerResponse;
import com.naipunya.ecommerce.order.PaymentMethod;

import java.math.BigDecimal;

public record PaymentRequest(
        BigDecimal amount,
        PaymentMethod paymentMethod,
        Integer orderId,
        String orderReference,
        CustomerResponse customer
) {
}
