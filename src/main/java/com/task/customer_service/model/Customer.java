package com.task.customer_service.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.stereotype.Indexed;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

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
public class Customer   {

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


    @Column(name = "mobile_number",  unique = true)
    private String mobileNumber;

    @Column(name = "bvn", unique = true)
    private String bvn;

    @Column(name = "nin", unique = true)
    private String nin;

    @Column(name = "bvn_validated")
    private boolean bvnValidated;

    @Basic
    @Column(name = "gender")
    private String gender;

    @Basic
    @Column(name = "balance_before" )
    private BigDecimal balanceBefore;


    @Basic
    @Column(name = "balance_after" )
    private BigDecimal balanceAfter;


    @Basic
    @Column(name = "withdraw_amount")
    private BigDecimal withdrawAmount;

    @Basic
    @Column(name = "account_number" , unique = true)
    private double accountNumber;

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

