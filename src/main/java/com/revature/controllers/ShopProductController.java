package com.revature.controllers;

import com.revature.models.Seller;
import com.revature.models.Shop;
import com.revature.models.ShopProduct;
import com.revature.models.ShopProductResponse;
import com.revature.services.ShopProductServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController
public class ShopProductController {
    @Autowired
    private ShopProductServiceImpl sps;

    @GetMapping("/shop_products/{id}")
    public ResponseEntity<ShopProduct> getShopProductByShopId(@PathVariable ("id") String id) {
        Optional<ShopProduct> sp = sps.getShopProductById(Integer.parseInt(id));
        return ResponseEntity.of(sp);
    }

    @GetMapping("/shop_products")
    public List<ShopProduct> getAllShopProducts() {
        return sps.getAllShopProducts();
    }

    @GetMapping("/sellers/{shopProductId}")
    public ResponseEntity<List<ShopProductResponse>> getAllSellersForProduct(@PathVariable("shopProductId") String id)
    {
        List<ShopProductResponse> shops = sps.getSellersForProduct(Integer.parseInt(id));
        return new ResponseEntity<>(shops, HttpStatus.OK);
    }
}
