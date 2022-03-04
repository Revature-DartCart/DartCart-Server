package com.revature.services;

import com.revature.models.User;
import org.springframework.stereotype.Service;

@Service
public class CheckoutServiceImpl implements CheckoutService {

    /**
     * This method recieves a User with a full cart of items and compares
     * that cart with the ones saved in the database. If there is a mismatch,
     * we return the User with the saved cart to the frontend. If there is not
     * a mismatch, we perform the checkout process (create an invoice, empty cart).
     *
     * @param user        The user who is checking out
     * @return            User object that has a corrected cart
     */
    @Override
    public User checkout(User user) {
        return null;
    }
}
