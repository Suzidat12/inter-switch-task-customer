package com.task.customer_service.service;

import com.task.customer_service.dto.request.CustomerRequest;
import com.task.customer_service.dto.request.CustomerResponse;
import com.task.customer_service.dto.response.BvnVerificationResponse;
import com.task.customer_service.model.Customer;
import com.task.customer_service.repository.CustomerRepository;
import com.task.customer_service.utils.AppUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomerService {
    private final CustomerRepository customerRepository;
    private final AppUtils appUtils;
    private final PasswordEncoder passwordEncoder;

    // This endpoint will validate the bvn and create an account if the bvn is successfully validated
    public ResponseEntity<String> register(CustomerRequest request){
        Optional<Customer> customerOptional = customerRepository.findByBvn(request.getBvn());
        if(customerOptional.isPresent()){
            return ResponseEntity.badRequest().body("Bvn already exist");
        }
        BvnVerificationResponse bvnVerification = appUtils.verifyBvn1(request.getBvn());
//        if (Objects.isNull(bvnVerification)) {
//            return ResponseEntity.badRequest().body("Bvn not found");
//        }
        Customer customer = new Customer();
        customer.setBvn(request.getBvn());
        customer.setEmail(request.getEmail());
        customer.setGender(bvnVerification.getGender());
        customer.setNin(bvnVerification.getNin());
        customer.setFullName(bvnVerification.getFirstName().concat(" ").concat(bvnVerification.getLastName()));
        customer.setDob(bvnVerification.getDateOfBirth());
        customer.setBalanceBefore(BigDecimal.ZERO);
        customer.setBvnValidated(true);
        customer.setMobileNumber(bvnVerification.getMobileNumber());
        customer.setPassword(passwordEncoder.encode(request.getPassword()));
        customer.setBalanceAfter(BigDecimal.ZERO);
        customer.setWithdrawAmount(BigDecimal.ZERO);
        customer.setTransactionCount(0);
        customer.setTotalTransactionAmount(BigDecimal.ZERO);
        customer.setDateCreated(new Date());
        customer.setAccountNumber(appUtils.generateAccountNumber());
        customerRepository.save(customer);
        return ResponseEntity.ok("Account successfully created");
    }

}
