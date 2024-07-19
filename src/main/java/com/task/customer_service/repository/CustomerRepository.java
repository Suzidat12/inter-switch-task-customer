package com.task.customer_service.repository;

import com.task.customer_service.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;
@Repository
public interface CustomerRepository extends JpaRepository<Customer,Long> {
    Optional<Customer> findByBvn(String bvn);
}
