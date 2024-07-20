package com.task.customer.repository;

import com.task.customer.entity.Customer;
import com.task.customer.entity.Transactions;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class TransactionRepository {
    private List<Transactions> transactionList
            = new ArrayList<>();
    public Transactions addTransaction(Transactions transactions) {
        transactionList.add(transactions);
        return transactions;
    }

    public List<Transactions> findAll() {
        return transactionList;
    }

}
