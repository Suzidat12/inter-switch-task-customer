package com.task.customer.service;

import com.task.customer.dto.request.*;
import com.task.customer.dto.response.ApiResponse;
import com.task.customer.dto.response.ResponseDto;
import com.task.customer.entity.*;
import com.task.customer.repository.*;
import com.task.customer.utils.AppUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class BillsPaymentService {
    private final TransactionRepository transactionRepository;
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final TransferRepository transferRepository;
    private final CustomerRepository customerRepository;
    private final BillerRepository billerRepository;
    private final AppUtils appUtils;

    public ResponseEntity<ResponseDto<Transactions>> submitPayment(PaymentRequest paymentRequest) {
        Customer customer = appUtils.getAuthenticatedUser();

        if (customer == null || !appUtils.isCustomer(customer)) {
            // Return appropriate response if customer is not authenticated or not found
            return ResponseEntity.notFound().build();
        }

        try {
            Optional<Product> productOptional = Optional.ofNullable(productRepository.findByIds(paymentRequest.getProductId()));

            if (productOptional.isEmpty()) {
                // Return appropriate response if product is not found
                return ResponseEntity.notFound().build();
            }

            Product product = productOptional.get();

            // Create a new transaction
            Transactions transactions = new Transactions();
            transactions.setId(appUtils.generateUUID());
            transactions.setPaymentStatus("SUCCESSFUL"); // Hardcoded assumption, adjust as per actual implementation
            transactions.setProduct(product);
            transactions.setCustomerId(customer);
            transactions.setAmount(paymentRequest.getAmount());
            transactions.setTransDate(new Date());

            // Save the transaction
            transactionRepository.addTransaction(transactions);

            // Return success response with transaction details
            return ApiResponse.ok(transactions);
        } catch (Exception ex) {
            ex.printStackTrace(); // Log the exception for debugging purposes
            return ResponseEntity.internalServerError().build();
        }
    }

    public ResponseEntity<ResponseDto<Product>> addProduct(ProductRequest request){
        Customer customer = appUtils.getAuthenticatedUser();
        if (customer == null || !appUtils.isCustomer(customer)) {
            return ResponseEntity.notFound().build();
        }
        try {
            Optional<Biller> billerOptional = Optional.ofNullable(billerRepository.findByIds(request.getBillerId()));

            if (billerOptional.isEmpty()) {
                // Return appropriate response if product is not found
                return ResponseEntity.notFound().build();
            }
            Biller biller = billerOptional.get();
            // Create a new transaction
            Product product = new Product();
            product.setId(appUtils.generateUUID());
          product.setBiller(biller);
          product.setName(request.getProductName());
          productRepository.addProduct(product);
            return ApiResponse.ok(product);
        } catch (Exception ex) {
            ex.printStackTrace(); // Log the exception for debugging purposes
            return ResponseEntity.internalServerError().build();
        }

    }

    public ResponseEntity<ResponseDto<Biller>> addBill(BillerRequest request){
        Customer customer = appUtils.getAuthenticatedUser();
        if (customer == null || !appUtils.isCustomer(customer)) {
            return ResponseEntity.notFound().build();
        }
        try {
            Optional<Categories> categoryOptional = Optional.ofNullable(categoryRepository.findByIds(request.getCategoryId()));
            if (categoryOptional.isEmpty()) {
                // Return appropriate response if product is not found
                return ResponseEntity.notFound().build();
            }
            Categories categories = categoryOptional.get();
            // Create a new transaction
            Biller biller = new Biller();
            biller.setId(appUtils.generateUUID());
            biller.setName(request.getBillerName());
           biller.setCategory(categories);
            billerRepository.addBill(biller);
            return ApiResponse.ok(biller);
        } catch (Exception ex) {
            ex.printStackTrace(); // Log the exception for debugging purposes
            return ResponseEntity.internalServerError().build();
        }

    }

    public ResponseEntity<ResponseDto<Categories>> addCategory(CategoryRequest request){
        Customer customer = appUtils.getAuthenticatedUser();
        if (customer == null || !appUtils.isCustomer(customer)) {
            return ResponseEntity.notFound().build();
        }
        try {
            Categories category = new Categories();
            category.setId(appUtils.generateUUID());
            category.setName(request.getCategoryName());
           categoryRepository.addCategory(category);
            return ApiResponse.ok(category);
        } catch (Exception ex) {
            ex.printStackTrace(); // Log the exception for debugging purposes
            return ResponseEntity.internalServerError().build();
        }

    }

    public List<Customer> getDashBoard(){
        return customerRepository.findAll();
    }

    public List<Biller> getBiller(){
        return billerRepository.findAll();
    }

    public List<Categories> getCategory(){
        return categoryRepository.findAll();
    }
    public List<Product> getProduct(){
        return productRepository.findAll();
    }

    public List<Transactions> getTransaction(){
        return transactionRepository.findAll();
    }

    public ResponseEntity<ResponseDto<Transfer>> submitTransfer(TransferRequest request) {
        Customer customer = appUtils.getAuthenticatedUser();
        if (customer == null || !appUtils.isCustomer(customer)) {
            // Return appropriate response if customer is not authenticated or not found
            return ResponseEntity.notFound().build();
        }

        try {
            Optional<Customer> customerOptional = Optional.ofNullable(customerRepository.findByIds(request.getCustomerId()));

            if (customerOptional.isEmpty()) {
                // Return appropriate response if product is not found
                return ResponseEntity.notFound().build();
            }

            Customer customer1 = customerOptional.get();
            Transfer transfer = new Transfer();
            transfer.setId(appUtils.generateUUID());
            transfer.setCustomer(customer1);
            transfer.setBankName(request.getBankName());
            transfer.setToAccount(request.getToAccount());
            transfer.setAmount(request.getAmount());
            transfer.setFromAccount(request.getFromAccount());
            transfer.setTransferDate(new Date());
            transferRepository.makeTransfer(transfer);

            // Return success response with transaction details
            return ApiResponse.ok(transfer);
        } catch (Exception ex) {
            ex.printStackTrace(); // Log the exception for debugging purposes
            return ResponseEntity.internalServerError().build();
        }
    }
    public List<Transfer> getTransfer(){
        return transferRepository.findAll();
    }
    public List<String> getUniqueBanksFromTransfers() {
        Set<String> uniqueBanks = new HashSet<>();
        for (Transfer transfer : getTransfer()) {
            uniqueBanks.add(transfer.getBankName());
        }
        return new ArrayList<>(uniqueBanks);
    }
}
