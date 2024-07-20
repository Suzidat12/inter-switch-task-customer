package com.task.customer.repository;

import com.task.customer.entity.Biller;
import com.task.customer.entity.Customer;
import com.task.customer.entity.Product;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class BillerRepository {
    private List<Biller> billerList
            = new ArrayList<>();
    public Biller addBill(Biller biller) {
        billerList.add(biller);
        return biller;
    }
    public Biller findByName(String name) {
        Optional<Biller> foundBill = billerList.stream()
                .filter(bill -> bill.getName().equals(name))
                .findFirst();
        return foundBill.orElseThrow(() -> new RuntimeException("Biller with name" + name + " already exist"));
    }


    public Biller findByIds(String id) {
        return billerList.stream()
                .filter(bill ->
                        bill.getId().equals(id))
                .findFirst()
                .orElseThrow(()->new RuntimeException("Biller not found"));
    }

    public List<Biller> findAll() {
        return billerList;
    }

}
