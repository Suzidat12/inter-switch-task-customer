package com.task.customer.controller;

import com.task.customer.dto.request.*;
import com.task.customer.dto.response.ResponseDto;
import com.task.customer.entity.*;
import com.task.customer.service.BillsPaymentService;
import com.task.customer.service.CustomerService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/biller")
@RequiredArgsConstructor
public class BillController {
    private final BillsPaymentService billsPaymentService;

    @PostMapping("/submit-payment")
    public ResponseEntity<ResponseDto<Transactions>> submitPayment(@RequestBody PaymentRequest paymentRequest) {
        return billsPaymentService.submitPayment(paymentRequest);
    }

    @PostMapping("/add-product")
    public ResponseEntity<ResponseDto<Product>> addProduct(@RequestBody ProductRequest request) {
        return billsPaymentService.addProduct(request);

    }

    @PostMapping("/add-biller")
    public ResponseEntity<ResponseDto<Biller>> addBill(@RequestBody BillerRequest request) {
        return billsPaymentService.addBill(request);
    }

    @GetMapping("/dashboard")
    public ResponseEntity<List<Customer>> getCustomerInfo() {
        return ResponseEntity.ok(billsPaymentService.getDashBoard());
    }

    @GetMapping("/product")
    public ResponseEntity<List<Product>> getProductInfo() {
        return ResponseEntity.ok(billsPaymentService.getProduct());
    }

    @GetMapping("/list-biller")
    public ResponseEntity<List<Biller>> getBillerInfo() {
        return ResponseEntity.ok(billsPaymentService.getBiller());
    }

    @GetMapping("/categories")
    public ResponseEntity<List<Categories>> getCategoriesInfo() {
        return ResponseEntity.ok(billsPaymentService.getCategory());
    }

    @GetMapping("/transactions")
    public ResponseEntity<List<Transactions>> getTransactionInfo() {
        return ResponseEntity.ok(billsPaymentService.getTransaction());
    }

    @GetMapping("/transfer")
    public ResponseEntity<List<Transfer>> getTransferInfo() {
        return ResponseEntity.ok(billsPaymentService.getTransfer());
    }

    @GetMapping("/bank")
    public ResponseEntity<List<String>> getBankInfo() {
        return ResponseEntity.ok(billsPaymentService.getUniqueBanksFromTransfers());
    }
    @PostMapping("/add-category")
    public ResponseEntity<ResponseDto<Categories>> addCategory(@RequestBody CategoryRequest request) {
        return billsPaymentService.addCategory(request);
    }

    @PostMapping("/add-transfer")
    public ResponseEntity<ResponseDto<Transfer>> submitTransfer(@RequestBody TransferRequest request) {
        return billsPaymentService.submitTransfer(request);
    }

}
