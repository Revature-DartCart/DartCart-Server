package com.revature.repositories;

import com.revature.models.Shop;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShopRepo extends CrudRepository<Shop, Integer> {

}
