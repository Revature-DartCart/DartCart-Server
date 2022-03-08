package com.revature.repositories;

import com.revature.models.Product;
import java.util.List;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepo extends CrudRepository<Product, Integer> {
  public List<Product> findByCategories(String category);
}
