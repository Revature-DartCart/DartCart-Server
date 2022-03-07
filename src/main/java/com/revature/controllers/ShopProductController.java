package com.revature.controllers;

import com.revature.models.Shop;
import com.revature.models.ShopProduct;
import com.revature.services.ShopProductServiceImpl;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
public class ShopProductController {
    @Autowired
    private ShopProductServiceImpl sps;

    @GetMapping("/shop_products/{id}")
    public ResponseEntity<ShopProduct> getShopProductByShopId(@PathVariable("id") String id) {
        Optional<ShopProduct> sp = sps.getShopProductById(Integer.parseInt(id));
        return ResponseEntity.of(sp);
    }

    @PostMapping(value = "/shop_products", consumes = "application/json", produces = "application/json")
    public ResponseEntity<ShopProduct> newShopProduct(@RequestBody ShopProduct s) {
        try {
            ShopProduct created = sps.addShopProduct(s);

            if (created.getId() != 0) {
                return new ResponseEntity<>(created, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
        } catch (DataIntegrityViolationException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/shop_products")
    public List<ShopProduct> getAllShopProducts() {
        return sps.getAllShopProducts();
    }
}
