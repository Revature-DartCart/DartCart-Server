package com.revature.services;

import com.revature.dartcart.DartCartApplication;
import com.revature.models.User;
import com.revature.repositories.UserRepo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK, classes = DartCartApplication.class)
class UserServiceTest {

    final private User mockUser = new User(1,"test1","password","Test","User","test1@DartCart.net","123-456-7890",123563672L);

    @Autowired
    private WebApplicationContext webApplicationContext;
    @MockBean
    private UserRepo mockUserRepo;
    @Autowired
    private UserService mockUserService;
    @Autowired
    BCryptPasswordEncoder bCryptEncoder;


    @BeforeEach
    void setup()
    {
        MockMvc mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    void newUser()
    {
        User encrypted = mockUser;
        encrypted.setPassword(bCryptEncoder.encode(encrypted.getPassword()));
        Mockito.when(mockUserRepo.save(mockUser)).thenReturn(encrypted);
        Assertions.assertEquals(encrypted,mockUserService.newUser(mockUser));
    }
}