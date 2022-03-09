package com.revature.e2e;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.driver.DartCartApplication;
import com.revature.models.*;
import java.util.ArrayList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithAnonymousUser;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK, classes = DartCartApplication.class)
public class CheckoutEndToEndTests {
    private MockMvc mvc;
    private ObjectMapper mapper = new ObjectMapper();

    @Autowired
    private WebApplicationContext webApplicationContext;

    private User user;
    private Seller seller;
    private Shop shop;
    private Category food;
    private Category electronics;
    private Product kellogs;
    private Product laptop;
    private ShopProduct shopKellogs;
    private ShopProduct shopLaptop;

    @BeforeEach
    void setup() {
        mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).apply(springSecurity()).build();
    }

    @BeforeEach
    public void init() {
        user =
            new User(
                1,
                "HughTheMann",
                null,
                "Hugh",
                "Mann",
                "hughmanntheman@gmail.com",
                "757-319-0192",
                "address",
                1645736771148L,
                new ArrayList<>()
            );

        seller =
            new Seller(
                1,
                "name",
                "/sellers/bestseller",
                "THE BEST SELLER!",
                new User(
                    2,
                    "Sammykins",
                    null,
                    "Samantha",
                    "Mann",
                    "sammannnotthefish@gmail.com",
                    "757-978-6422",
                    "address",
                    1645737413547L,
                    new ArrayList<>()
                )
            );

        shop = new Shop(1, "location", seller);

        food = new Category(1, "Food");
        electronics = new Category(2, "Electronics");

        kellogs = new Product(1, "Kelloggs Froot Loops", "Delicious frooty flava", new ArrayList<>());
        kellogs.getCategories().add(food);

        laptop = new Product(2, "Laptop", "does computer stuff", new ArrayList<>());

        laptop.getCategories().add(electronics);

        shopKellogs = new ShopProduct(1, 10, 15, 2, shop, kellogs);

        shopLaptop = new ShopProduct(2, 10, 15, 2, shop, laptop);
    }

    @Test
    @WithAnonymousUser
    public void testUnauthorizedUser() throws Exception {
        mvc
            .perform(
                MockMvcRequestBuilders
                    .post("/checkout")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(mapper.writeValueAsString(user))
            )
            .andExpect(MockMvcResultMatchers.status().isUnauthorized());
    }

    @Test
    @WithMockUser("HughTheMann")
    public void testUserCheckoutWithValidCart() throws Exception {
        CartItem cartKellogs = new CartItem(1, 1, false, user, shopKellogs);
        CartItem cartLaptop = new CartItem(2, 1, false, user, shopLaptop);
        user.getItemList().add(cartKellogs);
        user.getItemList().add(cartLaptop);

        mvc
            .perform(
                MockMvcRequestBuilders
                    .post("/checkout")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(mapper.writeValueAsString(user))
            )
            .andExpectAll(MockMvcResultMatchers.status().isOk());
    }

    @Test
    @WithMockUser("HughTheMann")
    public void testUserCheckoutWithInvalidCart() throws Exception {
        CartItem cartKellogs = new CartItem(1, 1, false, user, shopKellogs);
        user.getItemList().add(cartKellogs);

        mvc
            .perform(
                MockMvcRequestBuilders
                    .post("/checkout")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(mapper.writeValueAsString(user))
            )
            .andExpectAll(MockMvcResultMatchers.status().isBadRequest());
    }
}
