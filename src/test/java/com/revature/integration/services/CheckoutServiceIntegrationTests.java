package com.revature.integration.services;

import com.revature.driver.DartCartApplication;
import com.revature.exceptions.BadTransactionException;
import com.revature.models.*;
import com.revature.services.CheckoutService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK, classes = DartCartApplication.class)
public class CheckoutServiceIntegrationTests {

    @Autowired
    private CheckoutService checkoutService;

    private User user;
    private Shop shop;
    private Category food;
    private Category electronics;
    private Product kellogs;
    private Product laptop;
    private ShopProduct shopKellogs;
    private ShopProduct shopLaptop;

    @BeforeEach
    public void init() {
        user = new User(
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

        shop = new Shop(
                1,
                "location",
                null
        );

        food = new Category(1, "Food");
        electronics = new Category(2, "Electronics");

        kellogs = new Product(
                1,
                "Kelloggs Froot Loops",
                "Delicious frooty flava",
                new ArrayList<>()
        );
        kellogs.getCategories().add(food);

        laptop = new Product(
                2,
                "Laptop",
                "does computer stuff",
                new ArrayList<>()
        );

        laptop.getCategories().add(electronics);

        shopKellogs = new ShopProduct(
                1,
                10,
                15,
                2,
                shop,
                kellogs
        );

        shopLaptop = new ShopProduct(
                2,
                10,
                15,
                2,
                shop,
                laptop
        );
    }

    @Test
    public void testUserCheckoutWithValidCart() throws BadTransactionException {
        CartItem cartKellogs = new CartItem(1, 1, false, user, shopKellogs);
        CartItem cartLaptop = new CartItem(2, 1, false, user, shopLaptop);
        user.getItemList().add(cartKellogs);
        user.getItemList().add(cartLaptop);

        User responseUser = checkoutService.checkout(user);

        assertEquals(0, responseUser.getItemList().size());
    }

    @Test
    public void testUserCheckoutWithInvalidCart() throws BadTransactionException {
        CartItem cartKellogs = new CartItem(1, 1, false, user, shopKellogs);
        user.getItemList().add(cartKellogs);

        User responseUser = checkoutService.checkout(user);

        assertEquals(2, responseUser.getItemList().size());
    }
}
