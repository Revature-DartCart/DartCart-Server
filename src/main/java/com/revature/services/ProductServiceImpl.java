package com.revature.services;

import com.revature.models.Product;
import com.revature.repositories.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService{
    @Autowired
    ProductRepo pr;

    @Override
    public Optional<Product> getProductById(int productId) {
        return pr.findById(productId);
    }

}