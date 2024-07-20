package com.task.customer.controller;

import com.task.customer.dto.request.CustomerRequest;
import com.task.customer.dto.request.LoginRequest;
import com.task.customer.dto.response.JwtAuthenticationResponse;
import com.task.customer.entity.Customer;
import com.task.customer.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customer")
@RequiredArgsConstructor
public class CustomerController {
    private final CustomerService customerService;
    @PostMapping("/register")
    public ResponseEntity<String> registerAccount(@RequestBody CustomerRequest request){
        return customerService.register(request);
    }


@PostMapping("/login")
    public ResponseEntity<JwtAuthenticationResponse> login(@RequestBody LoginRequest request){
        return ResponseEntity.ok(customerService.login(request));
}

}
