package com.task.customer.repository;

import com.task.customer.entity.Customer;
import com.task.customer.entity.Product;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class CustomerRepository {
    private List<Customer> customers
            = new ArrayList<>();
    public Customer addCustomer(Customer customer) {
        customers.add(customer);
        return customer;
    }
    public Customer findByBvn(String bvn) {
        Optional<Customer> foundCustomer = customers.stream()
                .filter(customer -> customer.getBvn().equals(bvn))
                .findFirst();
        return foundCustomer.orElseThrow(() -> new RuntimeException("Customer with BVN " + bvn + " already exist"));
    }

    public Customer findByEmail(String email) {
        Optional<Customer> foundCustomer = customers.stream()
                .filter(customer -> customer.getEmail().equals(email))
                .findFirst();
        return foundCustomer.orElseThrow(() -> new RuntimeException("Customer with Email " + email + " already exist"));
    }

    public Optional<Customer> findByMail(String email) {
        return customers.stream()
                .filter(customer -> customer.getEmail().equals(email))
                .findFirst();
    }
    public Customer findByIds(String id) {
        return customers.stream()
                .filter(customer ->
                        customer.getId().equals(id))
                .findFirst()
                .orElseThrow(()->new RuntimeException("Customer not found"));
    }
    public List<Customer> findAll() {
        return customers;
    }

}
