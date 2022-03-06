package com.revature.controllers;

import com.revature.services.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
public class SellerController {
  @Autowired
  SellerService sellerService;
  // There's no need for an addSeller() method. It's handled by the /signup endpoint in ShopController,
  // since by signing up as a Seller, you also create your first Shop automatically.

}
