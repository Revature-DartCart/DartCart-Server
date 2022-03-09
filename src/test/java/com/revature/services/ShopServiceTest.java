package com.revature.services;

import com.revature.driver.DartCartApplication;
import com.revature.models.Seller;
import com.revature.models.Shop;
import com.revature.models.User;
import com.revature.repositories.SellerRepo;
import com.revature.repositories.ShopRepo;
import java.util.ArrayList;
import java.util.Optional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK, classes = DartCartApplication.class)
class ShopServiceTest {
    @MockBean
    private ShopRepo sr;

    @Autowired
    private ShopServiceImpl mockShopService;

    private final User mockUser = new User(
        1,
        "test1",
        "password",
        "Test",
        "User",
        "test1@DartCart.net",
        "123-456-7890",
        "1 Test Street, Test Town, Testonia 12345",
        123563672L,
        null
    );
    private ArrayList<Shop> shops;

    private final Seller mockSeller = new Seller(1, "test", "test.com", "testDesc", mockUser);

    private final Shop mockShop = new Shop(1, "here", mockSeller);

    @BeforeEach
    void setup() {
        shops = new ArrayList<>();
        shops.add(mockShop);
    }

    @Test
    void addShop() {
        Mockito.when(sr.save(mockShop)).thenReturn(mockShop);
        Assertions.assertEquals(mockShop, mockShopService.addShop(mockShop));
    }

    @Test
    void getShopById() {
        Mockito.when(sr.findById(mockShop.getId())).thenReturn(Optional.of(mockShop));
        Assertions.assertEquals(mockShop, mockShopService.getShopById(mockShop.getId()).get());
    }

    @Test
    void getAllShops() {
        Mockito.when(sr.findAll()).thenReturn(shops);
        Assertions.assertEquals(shops, mockShopService.getAllShops());
    }

    @Test
    void deleteShop() {
        Assertions.assertTrue(mockShopService.deleteShop(mockShop.getId()));
        Mockito.doThrow(new IllegalArgumentException()).when(sr).deleteById(0);
        Assertions.assertFalse(mockShopService.deleteShop(0));
    }
}
