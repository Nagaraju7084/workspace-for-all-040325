package com.naipunya.ecommerce.kafka;

import com.naipunya.ecommerce.customer.CustomerResponse;
import com.naipunya.ecommerce.order.PaymentMethod;
import com.naipunya.ecommerce.product.PurchaseResponse;

import java.math.BigDecimal;
import java.util.List;

public record OrderConfirmation(
        String orderReference,
        BigDecimal totalAmount,
        PaymentMethod paymentMethod,
        CustomerResponse customer,
        List<PurchaseResponse> products
) {
}
