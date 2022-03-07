package com.revature.repositories;


import com.revature.models.Product;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
    public interface ProductRepo extends CrudRepository<Product, Integer> {

    public List<Product> findByCategories(String category);

}
