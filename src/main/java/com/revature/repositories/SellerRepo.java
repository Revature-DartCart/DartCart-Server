package com.revature.repositories;

import com.revature.models.Seller;
import java.util.List;
import java.util.Optional;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SellerRepo extends CrudRepository<Seller, Integer> {
  Optional<Seller> findByUserId(int userId);
}
