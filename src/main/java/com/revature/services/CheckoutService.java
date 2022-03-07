package com.revature.services;

import com.revature.exceptions.BadTransactionException;
import com.revature.models.User;

public interface CheckoutService {

    public User checkout(User user) throws BadTransactionException;
}
