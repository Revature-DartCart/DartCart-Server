package com.revature.services;

import com.revature.models.Seller;
import java.util.List;
import java.util.Optional;

public interface SellerService {
  public Seller addSeller(Seller seller);
  public Optional<Seller> getSellerById(int id);
  public List<Seller> getAllSellers();
  public void updateSeller(Seller change);
  public boolean deleteSeller(int id);

  public Optional<Seller> getSellerByUserId(int id);
}
