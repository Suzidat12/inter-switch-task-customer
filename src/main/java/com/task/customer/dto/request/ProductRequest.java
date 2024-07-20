package com.task.customer.dto.request;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ProductRequest {
    private String productName;
   private String billerId;
}
