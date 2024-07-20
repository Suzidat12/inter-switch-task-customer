package com.task.customer.entity;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Date;
import java.util.List;

@Getter
@Setter
public class Customer implements UserDetails {

    private Long id;
    private String profilePicUrl;
    private String fullName;
    private String email;
    private String password;
    private String dob;
    private String role;
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

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("USER"));
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
