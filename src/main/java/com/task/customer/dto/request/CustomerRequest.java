package com.task.customer.dto.request;

import lombok.Data;

@Data
public class CustomerRequest {
    private String email;
    private String password;
    private String bvn;
}
