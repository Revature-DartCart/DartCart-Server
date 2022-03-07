package com.revature.repositories;

import com.revature.models.Product;
import com.revature.models.ShopProduct;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShopProductRepo extends CrudRepository<ShopProduct, Integer> {

    List<ShopProduct> findByProduct(Product product);
}