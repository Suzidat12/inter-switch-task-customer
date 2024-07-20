package com.task.customer.entity;

import com.task.customer.service.CustomerService;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class Transfer {
    private String id;
    private String bankName;
    private BigDecimal amount;
    private String fromAccount;
    private String toAccount;
    private Customer customer;
    private Date transferDate;
}
