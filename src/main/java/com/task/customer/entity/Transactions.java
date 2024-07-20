package com.task.customer.entity;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class Transactions {
    private String id;
    private Customer customerId;
    private BigDecimal amount;
    private Product product;
    private Date transDate;
    private String paymentStatus;

}
