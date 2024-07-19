package com.task.customer_service.controller;

import com.task.customer_service.dto.request.CustomerRequest;
import com.task.customer_service.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/customer")
@RequiredArgsConstructor
public class CustomerController {
    private final CustomerService customerService;
    @PostMapping("/register")
    public ResponseEntity<String> registerAccount(@RequestBody CustomerRequest request){
        return customerService.register(request);
    }

}
