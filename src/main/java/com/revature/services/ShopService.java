package com.revature.services;

import com.revature.models.Seller;
import com.revature.models.Shop;
import java.util.List;
import java.util.Optional;

public interface ShopService {
  public Shop addShop(Shop shop);
  public Optional<Shop> getShopById(int id);
  public List<Shop> getAllShops();
  public void updateShop(Shop change);
  public boolean deleteShop(int id);
}
