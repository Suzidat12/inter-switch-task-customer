package com.task.customer.entity;

import lombok.Data;

@Data
public class Product {
    private String id;
    private String name;
    private Biller biller;
}
