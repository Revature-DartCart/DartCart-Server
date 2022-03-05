package com.revature.controllers;

import com.revature.models.Shop;
import com.revature.services.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
public class ShopController {

    @Autowired
    ShopService shopService;

    @PostMapping(value = "/signup", consumes = "application/json", produces = "application/json")
    public ResponseEntity<Shop> newShop(@RequestBody Shop s) {
        try {
            Shop created = shopService.addShop(s);

            if (created.getId() != 0) {
                return new ResponseEntity<>(created, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.I_AM_A_TEAPOT);
            }
        } catch (DataIntegrityViolationException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

}
