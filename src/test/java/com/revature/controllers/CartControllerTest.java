package com.revature.controllers;


import com.revature.driver.DartCartApplication;
import com.revature.models.CartItem;
import com.revature.models.Shop;
import com.revature.models.ShopProduct;
import com.revature.models.User;
import com.revature.services.CartItemService;
import com.revature.services.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK, classes = DartCartApplication.class)
public class CartControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    WebApplicationContext webApplicationContext;

    @MockBean
    CartItemService cartItemService;

    @MockBean
    UserService userService;

    @BeforeEach
    void setup(){
        mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }


    final static List<CartItem> cartItems = new ArrayList<>();
    {
        cartItems.add(cartItem);
    }
    final static CartItem cartItem = new CartItem(1, true,
            new User(1,"test",
                    "$2a$04$vwsthDC7JUu9kfqA264DJenCuUC2ifUcRAGdEUFNrxa/gz9p9rgC2",
                    "Jer",
                    "Mann",
                    "jermanny@gmail.com",
                    "757-670-8879",
                    "address",
                    1645737745123L,null),

            new ShopProduct());

    final static User user = new User();
    {
        user.setUsername("test");
        user.setId(1);
    }






    @Test
    void  allCartItems() throws Exception{
        List<CartItem> cartItems = new ArrayList<>();
        cartItems.add(cartItem);


        Mockito.when(cartItemService.getAllCartItem()).thenReturn(cartItems);
        ResultActions resultActions = mvc.perform(MockMvcRequestBuilders.get("/carts"));
        resultActions.andExpectAll(MockMvcResultMatchers.status().isOk(),
                MockMvcResultMatchers.jsonPath("$[0]").value(cartItem));
    }

    @Test
    void getCartByUsername() throws Exception{
        List<CartItem> cartItemList =new ArrayList<>();
        cartItemList.add(cartItem);

        System.out.println(cartItemList);
        Mockito.when(cartItemService.getAllCartItem(1))
                .thenReturn(cartItemList);

        ResultActions resultActions = mvc.perform(MockMvcRequestBuilders.get("/carts/test")
                .contentType(MediaType.APPLICATION_JSON));

        resultActions.andExpectAll(
                MockMvcResultMatchers.status().isOk(),
                MockMvcResultMatchers.jsonPath("$[0]").value(cartItem)
        ).andDo(print());
    }


    @Test
    void addCartItem() throws Exception {
        CartItem cartItem1 = new CartItem();
        cartItem1.setId(1);
        cartItem1.setCustomer(user);

        Mockito.when(cartItemService.addCartItem(cartItem1)).thenReturn(cartItem);

        ResultActions resultActions = mvc.perform(MockMvcRequestBuilders.post("/carts")
                .contentType(MediaType.APPLICATION_JSON));

        resultActions.andExpectAll(
                MockMvcResultMatchers.status().isOk()).andDo(print());
    }
}
