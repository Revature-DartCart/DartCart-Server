package com.revature.controllers;

import com.revature.services.AuthService;
import com.revature.utilities.JwtTokenUtil;
import com.revature.models.UserLogin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.security.core.Authentication;

@RestController
@CrossOrigin
public class AuthController {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    JwtTokenUtil jwtTokenUtil;

    @Autowired
    AuthService authService;


    @PostMapping("login")
    public ResponseEntity<User> login(@RequestBody UserLogin request) {
        try {
            Authentication authenticate = authenticationManager
                    .authenticate(
                            new UsernamePasswordAuthenticationToken(
                                    request.getUsername(), request.getPassword()
                            )
                    );

            User user = (User) authenticate.getPrincipal();
//            if(!request.getPassword().equals(user.getPassword())){
//                throw new BadCredentialsException("wrong password");
//            }
            System.out.println(jwtTokenUtil.validate(jwtTokenUtil.generateAccessToken(user)));

            return ResponseEntity.ok()
                    .header(
                            HttpHeaders.AUTHORIZATION,
                            jwtTokenUtil.generateAccessToken(user)
                    )
                    .body(user);
        } catch (BadCredentialsException ex) {

            System.out.println("username:"+request.getUsername());
            System.out.println("password:"+request.getPassword());

            System.out.println("in database:"+ authService.loadUserByUsername(request.getUsername()));



            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }

//        System.out.println("username:"+request.getUsername());
//        System.out.println("password:"+request.getPassword());
//        System.out.println("in database:"+userRepo.findAll());

    }
}
