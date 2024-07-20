package com.task.customer.repository;

import com.task.customer.entity.Biller;
import com.task.customer.entity.Categories;
import com.task.customer.entity.Customer;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class CategoryRepository {
    private List<Categories> categotyList
            = new ArrayList<>();
    public Categories addCategory(Categories categories) {
        categotyList.add(categories);
        return categories;
    }

    public Categories findByIds(String id) {
        return categotyList.stream()
                .filter(categories ->
                        categories.getId().equals(id))
                .findFirst()
                .orElseThrow(()->new RuntimeException("Biller not found"));
    }

    public List<Categories> findAll() {
        return categotyList;
    }

}
