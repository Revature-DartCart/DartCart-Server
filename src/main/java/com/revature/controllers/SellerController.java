package com.revature.controllers;

import com.revature.models.Product;
import com.revature.models.Seller;
import com.revature.models.Shop;
import com.revature.services.SellerService;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
public class SellerController {

    @Autowired
    SellerService sellerService;

    // There's no need for an addSeller() method. It's handled by the /signup endpoint in ShopController,
    // since by signing up as a Seller, you also create your first Shop automatically.

    @GetMapping("/sellers/{id}")
    public ResponseEntity<Seller> getSellerById(@PathVariable("id") String id) {
        Optional<Seller> seller = sellerService.getSellerByUserId(Integer.parseInt(id));
        return seller
            .map(value -> new ResponseEntity<>(value, HttpStatus.OK))
            .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}
