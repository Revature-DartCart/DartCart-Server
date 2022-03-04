package com.revature.controllers;

import com.revature.models.User;
import com.revature.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLIntegrityConstraintViolationException;

@CrossOrigin
@RestController
public class UserController {

    @Autowired
    UserService us;

    // Return JWT for automatic login after registration
    @PostMapping(value = "/register", consumes = "application/json", produces = "application/json")
    public ResponseEntity<User> newUser(@RequestBody User u) {
        try {
            User created = us.addUser(u);
            if (created.getId() != 0) {
                return new ResponseEntity<>(created, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
        } catch (DataIntegrityViolationException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    // Take in user with cartitems list filled and give it to the checkout
    // service. Return a user with an empty cart if checkout completed
    // or return one with items in cart if there was a mismatch
    @PostMapping(value = "/checkout")
    public ResponseEntity<User> checkout(@RequestBody User user) {
        return null;
    }
}
