package com.revature.services;

import com.revature.models.User;
import com.revature.repositories.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService
{
    @Autowired
    UserRepo ur;

    @Autowired
    BCryptPasswordEncoder bCryptEncoder;

    public User newUser(User u)
    {
        u.setPassword(bCryptEncoder.encode(u.getPassword()));
        return ur.save(u);
    }

}
