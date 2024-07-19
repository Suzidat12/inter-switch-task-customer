package com.task.customer_service.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.stereotype.Indexed;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Indexed
@Table(name = "customer")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@JsonIgnoreProperties(ignoreUnknown = true)
@SequenceGenerator(
        name = "customer_sequence_gen",
        sequenceName = "customer_seq",
        allocationSize = 1)
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "customer_sequence_gen")
    private Long id;

    @Column(name = "profile_pic_url")
    private String profilePicUrl;

    @Column(name = "full_name")
    private String fullName;

    @Column(name = "email", unique = true)
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "dob")
    private String dob;

    @Column(name = "mobile_number", unique = true)
    private String mobileNumber;
    @JsonIgnore
    @Column(name = "bvn", unique = true)
    private String bvn;
    @JsonIgnore
    @Column(name = "nin", unique = true)
    private String nin;

    @Column(name = "bvn_validated")
    private boolean bvnValidated;


    @Column(name = "gender")
    private String gender;


    @Column(name = "balance_before")
    private BigDecimal balanceBefore;

    @Column(name = "balance_after")
    private BigDecimal balanceAfter;


    @Column(name = "withdraw_amount")
    private BigDecimal withdrawAmount;


    @Column(name = "account_number", unique = true)
    private String accountNumber;

    @JsonIgnore
    @Column(name = "transaction_count")
    @Builder.Default
    private long transactionCount = 0;

    @JsonIgnore
    @Column(name = "transaction_amount")
    @Builder.Default
    private BigDecimal totalTransactionAmount = BigDecimal.ZERO;

    @Column(name = "date_created")
    private Date dateCreated;

    @Column(name = "date_modify")
    private Date dateModify;

    @Column(name = "transaction_date")
    private Date transactionDate;

    public void incrementTransactionCount() {
        transactionCount++;
    }

    public void incrementTotalTransactionAmount(BigDecimal transactionAmount) {
        this.totalTransactionAmount = this.totalTransactionAmount.add(transactionAmount);
    }


}

