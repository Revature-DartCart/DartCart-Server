package com.revature.repositories;

import com.revature.models.ShopProduct;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShopProductRepo extends CrudRepository<ShopProduct, Integer> {}
