package com.revature.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.dartcart.DartCartApplication;
import com.revature.models.User;
import com.revature.services.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import static org.junit.jupiter.api.Assertions.*;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK, classes = DartCartApplication.class)

class UserControllerTest {
    private MockMvc mvc;
    ObjectMapper mapper = new ObjectMapper();
    @Autowired
    private WebApplicationContext webApplicationContext;
    @MockBean
    private UserService mockUserService;

    final private User mockUser = new User(1,"test1","password","Test","User","test1@DartCart.net","123-456-7890",123563672L);

    @BeforeEach
    void setup()
    {
        mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    void newUser() throws Exception {
        Mockito.when(mockUserService.newUser(mockUser)).thenReturn(mockUser);

        mvc.perform(MockMvcRequestBuilders.post("/register").
                        contentType(MediaType.APPLICATION_JSON).content(mapper.writeValueAsString(mockUser))).
                andExpect(MockMvcResultMatchers.status().isOk());

        Mockito.when(mockUserService.newUser(new User())).thenReturn(new User());
        mvc.perform(MockMvcRequestBuilders.post("/register").
                        contentType(MediaType.APPLICATION_JSON).
                        content(mapper.writeValueAsString(new User()))).
                        andExpect(MockMvcResultMatchers.status().isBadRequest());
    }
}