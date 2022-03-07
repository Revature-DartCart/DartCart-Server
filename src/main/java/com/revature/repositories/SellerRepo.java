package com.revature.repositories;

import com.revature.models.Seller;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SellerRepo extends CrudRepository<Seller, Integer> {

    Optional<Seller> findByUserId(int userId);

}
