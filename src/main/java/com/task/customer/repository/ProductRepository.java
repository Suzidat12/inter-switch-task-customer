package com.task.customer.repository;

import com.task.customer.entity.Customer;
import com.task.customer.entity.Product;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class ProductRepository {
    private List<Product> productList
            = new ArrayList<>();
    public Product addProduct(Product product) {
        productList.add(product);
        return product;
    }

    public Product findByIds(String id) {
        return productList.stream()
                .filter(product ->
                        product.getId().equals(id))
                .findFirst()
                .orElseThrow(()->new RuntimeException("Product not found"));
    }

    public List<Product> findAll() {
        return productList;
    }

}
