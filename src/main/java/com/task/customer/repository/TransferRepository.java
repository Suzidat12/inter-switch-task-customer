package com.task.customer.repository;

import com.task.customer.entity.Biller;
import com.task.customer.entity.Transfer;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class TransferRepository {
    private List<Transfer> transferList
            = new ArrayList<>();
    public Transfer makeTransfer(Transfer transfer) {
        transferList.add(transfer);
        return transfer;
    }
    public List<Transfer> findAll() {
        return transferList;
    }

}
