package com.revature.services;

import com.revature.models.ShopProduct;
import com.revature.repositories.ShopProductRepo;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ShopProductServiceImpl implements ShopProductService {
  @Autowired
  ShopProductRepo shopProductRepo;

  @Override
  public List<ShopProduct> getAllShopProducts() {
    return (List<ShopProduct>) shopProductRepo.findAll();
  }

  @Override
  public Optional<ShopProduct> getShopProductById(int id) {
    return shopProductRepo.findById(id);
  }
}
