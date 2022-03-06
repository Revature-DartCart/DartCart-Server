package com.revature.repositories;

import com.revature.models.Seller;
import com.revature.models.Shop;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ShopRepo extends CrudRepository<Shop, Integer> {
    Optional<Shop> findBySellerId(int id);
}
