package com.task.customer.dto.request;

import lombok.Data;

import java.math.BigDecimal;
@Data
public class PaymentRequest {
    BigDecimal amount;
    String productId;
}
