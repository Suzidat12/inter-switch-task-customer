package com.task.customer.dto.request;

import com.task.customer.entity.Customer;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class TransferRequest {
    private String bankName;
    private BigDecimal amount;
    private String fromAccount;
    private String toAccount;
    private String customerId;
}
