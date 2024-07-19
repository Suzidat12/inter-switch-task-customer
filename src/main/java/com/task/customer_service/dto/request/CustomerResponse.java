package com.task.customer_service.dto.request;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class CustomerResponse {
    private String profilePicUrl;

    private String fullName;

    private String email;

    private String mobileNumber;

    private String bvn;

    private String nin;

    private String gender;

    private BigDecimal balanceBefore;

    private BigDecimal balanceAfter;

    private BigDecimal withdrawAmount;

    private int accountNumber;


    private long transactionCount = 0;

    private BigDecimal totalTransactionAmount = BigDecimal.ZERO;

    private Date dateCreated;

}
