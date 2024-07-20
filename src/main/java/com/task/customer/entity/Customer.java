package com.task.customer.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Data
public class Customer {

    private Long id;
    private String profilePicUrl;
    private String fullName;
    private String email;
    private String password;
    private String dob;
    private String mobileNumber;
    private String bvn;
    private String nin;
    private boolean bvnValidated;
    private String gender;
    private BigDecimal balanceBefore;
    private BigDecimal balanceAfter;
    private BigDecimal withdrawAmount;
    private String accountNumber;
    @Builder.Default
    private long transactionCount = 0;
    @Builder.Default
    private BigDecimal totalTransactionAmount = BigDecimal.ZERO;
    private Date dateCreated;
    private Date dateModified;
    private Date transactionDate;

    public void incrementTransactionCount() {
        transactionCount++;
    }

    public void incrementTotalTransactionAmount(BigDecimal transactionAmount) {
        this.totalTransactionAmount = this.totalTransactionAmount.add(transactionAmount);
    }

}
