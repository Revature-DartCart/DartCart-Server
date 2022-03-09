package com.revature.repositories;

import com.revature.models.Shop;
import java.util.Optional;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShopRepo extends CrudRepository<Shop, Integer> {
    Optional<Shop> findBySellerId(int id);
}
