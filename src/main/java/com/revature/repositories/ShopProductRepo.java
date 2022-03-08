package com.revature.repositories;

import com.revature.models.Product;
import com.revature.models.ShopProduct;
import java.util.List;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShopProductRepo extends CrudRepository<ShopProduct, Integer> {
  List<ShopProduct> findByProduct(Product product);
}
