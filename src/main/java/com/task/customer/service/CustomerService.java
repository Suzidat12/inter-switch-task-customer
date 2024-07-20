package com.task.customer.service;

import com.task.customer.dto.request.CustomerRequest;
import com.task.customer.dto.request.LoginRequest;
import com.task.customer.dto.response.BvnVerificationResponse;


import com.task.customer.dto.response.JwtAuthenticationResponse;
import com.task.customer.entity.Customer;

import com.task.customer.repository.CustomerRepository;
import com.task.customer.utils.AppUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomerService {
 private final CustomerRepository customerRepository;
    private final AppUtils appUtils;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JWTService jwtService;

    // This endpoint will validate the bvn and create an account if the bvn is successfully validated
    public ResponseEntity<String> register(CustomerRequest request) {
        try {
            customerRepository.findByBvn(request.getBvn());
            return ResponseEntity.badRequest().body("BVN already exists");
        } catch (RuntimeException ex) {
            try {
                customerRepository.findByEmail(request.getEmail());
                return ResponseEntity.badRequest().body("Email already exists");
            } catch (RuntimeException emailEx) {
//        Optional<Customer> customerOptional = customerRepository.findByBvn(request.getBvn());
//        if(customerOptional.isPresent()){
//            return ResponseEntity.badRequest().body("Bvn already exist");
//        }
                BvnVerificationResponse bvnVerification = appUtils.verifyBvn1(request.getBvn());
//        if (Objects.isNull(bvnVerification)) {
//            return ResponseEntity.badRequest().body("Bvn not found");
//        }
                Customer customer = new Customer();
                customer.setBvn(request.getBvn());
                customer.setEmail(request.getEmail());
                //  customer.setGender(bvnVerification.getGender());
                //  customer.setNin(bvnVerification.getNin());
                // customer.setFullName(bvnVerification.getFirstName().concat(" ").concat(bvnVerification.getLastName()));
                //  customer.setDob(bvnVerification.getDateOfBirth());
                customer.setBalanceBefore(BigDecimal.ZERO);
                customer.setBvnValidated(true);
                //   customer.setMobileNumber(bvnVerification.getMobileNumber());
                customer.setPassword(passwordEncoder.encode(request.getPassword()));
                customer.setBalanceAfter(BigDecimal.ZERO);
                customer.setRole("USER");
                customer.setWithdrawAmount(BigDecimal.ZERO);
                customer.setTransactionCount(0);
                customer.setTotalTransactionAmount(BigDecimal.ZERO);
                customer.setDateCreated(new Date());
                customer.setAccountNumber(appUtils.generateAccountNumber());
                customerRepository.addCustomer(customer);
                return ResponseEntity.ok("Sucess");
            }
        }
    }

  public List<Customer> getDashBoard(){
       return customerRepository.findAll();
    }

    public JwtAuthenticationResponse login(LoginRequest request){
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                request.getEmail(),request.getPassword()));
        Customer user = customerRepository.findByMail(request.getEmail()).orElseThrow(()->new IllegalArgumentException("Invalid email or password ..."));
        String jwt = jwtService.generateToken(user);
        String refreshToken = jwtService.generateRefreshToken(new HashMap<>(),user);
        JwtAuthenticationResponse jwtAuthenticationResponse = new JwtAuthenticationResponse();
        jwtAuthenticationResponse.setToken(jwt);
        jwtAuthenticationResponse.setRefreshToken(refreshToken);
        return jwtAuthenticationResponse;
    }
}
