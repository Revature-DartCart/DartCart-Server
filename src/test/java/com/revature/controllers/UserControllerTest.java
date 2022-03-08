package com.revature.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.driver.DartCartApplication;
import com.revature.exceptions.BadTransactionException;
import com.revature.models.CartItem;
import com.revature.models.ShopProduct;
import com.revature.models.User;
import com.revature.services.CheckoutService;
import com.revature.services.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithAnonymousUser;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.ArrayList;

import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = DartCartApplication.class)
class UserControllerTest {

    private MockMvc mvc;
    ObjectMapper mapper = new ObjectMapper();

    @Autowired
    private WebApplicationContext webApplicationContext;

    @MockBean
    private UserService mockUserService;

    @MockBean
    private CheckoutService mockCheckoutService;

    final private User mockUser = new User(
            1,
            "test1",
            "password",
            "Test",
            "User",
            "test1@dartcart.net",
            "123-456-7890",
            "1 Test Street, Test Town, Testonia 12345",
            123563672L,
            new ArrayList<>()
    );

    @BeforeEach
    void setup() {
        mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).apply(springSecurity()).build();
    }

    @Test
    void newUser() throws Exception {
        when(mockUserService.addUser(mockUser)).thenReturn(mockUser);

        mvc.perform(MockMvcRequestBuilders.post("/register").
            contentType(MediaType.APPLICATION_JSON).content(mapper.writeValueAsString(mockUser))).
            andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void testNewUserFail() throws Exception {
        when(mockUserService.addUser(new User())).thenReturn(new User());
        mvc.perform(MockMvcRequestBuilders.post("/register").
                        contentType(MediaType.APPLICATION_JSON).
                        content(mapper.writeValueAsString(new User()))).
                andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    @WithAnonymousUser
    public void testUnauthorizedUser() throws Exception {
        mvc.perform(
                MockMvcRequestBuilders.post("/checkout")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(new User())))
                .andExpect(MockMvcResultMatchers.status().isUnauthorized());
    }

    @Test
    @WithMockUser("spring")
    public void testUserCheckoutWithValidCart() throws Exception {
        User user = new User(
                mockUser.getId(),
                mockUser.getUsername(),
                mockUser.getPassword(),
                mockUser.getFirstName(),
                mockUser.getLastName(),
                mockUser.getEmail(),
                mockUser.getPhone(),
                mockUser.getLocation(),
                mockUser.getRegistrationDate(),
                new ArrayList<>()
        );

        user.getItemList().add(new CartItem(
                1, 3, false, user, new ShopProduct(
                1, 5, 100, 100, null, null)));

        when(mockCheckoutService.checkout(Mockito.any(user.getClass()))).thenReturn(mockUser);

        mvc.perform(
                MockMvcRequestBuilders.post("/checkout")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(user)))
                .andExpectAll(
                        MockMvcResultMatchers.status().isOk(),
                        MockMvcResultMatchers.jsonPath("$.id").value(1)
                );
    }

    @Test
    @WithMockUser("spring")
    public void testUserCheckoutWithInvalidCart() throws Exception {
        User user = new User(
                mockUser.getId(),
                mockUser.getUsername(),
                mockUser.getPassword(),
                mockUser.getFirstName(),
                mockUser.getLastName(),
                mockUser.getEmail(),
                mockUser.getPhone(),
                mockUser.getLocation(),
                mockUser.getRegistrationDate(),
                new ArrayList<>()
        );

        user.getItemList().add(new CartItem(
                1, 3, false, user, new ShopProduct(
                1, 5, 100, 100, null, null)));

        when(mockCheckoutService.checkout(Mockito.any(User.class))).thenReturn(user);
        mvc.perform(
                MockMvcRequestBuilders.post("/checkout")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(user)))
                .andExpectAll(
                        MockMvcResultMatchers.status().isBadRequest(),
                        MockMvcResultMatchers.jsonPath("$.id").value(1)
                );
    }

    @Test
    @WithMockUser("spring")
    public void testUserWithInvalidQuantity() throws Exception {
        User user = new User(
                mockUser.getId(),
                mockUser.getUsername(),
                mockUser.getPassword(),
                mockUser.getFirstName(),
                mockUser.getLastName(),
                mockUser.getEmail(),
                mockUser.getPhone(),
                mockUser.getLocation(),
                mockUser.getRegistrationDate(),
                new ArrayList<>()
        );

        user.getItemList().add(new CartItem(
                1, 3, false, user, new ShopProduct(
                1, 5, 100, 100, null, null)));

        when(mockCheckoutService.checkout(Mockito.any(User.class))).thenThrow(BadTransactionException.class);
        mvc.perform(
                        MockMvcRequestBuilders.post("/checkout")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(mapper.writeValueAsString(user)))
                .andExpectAll(
                        MockMvcResultMatchers.status().isNotAcceptable()
                );
    }
}