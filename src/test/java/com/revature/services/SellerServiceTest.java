package com.revature.services;

import com.revature.driver.DartCartApplication;
import com.revature.models.Seller;
import com.revature.models.User;
import com.revature.repositories.SellerRepo;
import com.revature.services.SellerServiceImpl;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK, classes = DartCartApplication.class)
class SellerServiceTest {
    @MockBean
    private SellerRepo sr;

    @Autowired
    private SellerServiceImpl mockSellerService;

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
    private ArrayList<Seller> sellers;

    private final Seller mockSeller = new Seller(1, "test", "test.com", "testDesc", mockUser);

    @BeforeEach
    void setup() {
        sellers = new ArrayList<>();
        sellers.add(mockSeller);
    }

    @Test
    void addSeller() {
        Mockito.when(sr.save(mockSeller)).thenReturn(mockSeller);
        Assertions.assertEquals(mockSeller, mockSellerService.addSeller(mockSeller));
    }

    @Test
    void getSellerById() {
        Mockito.when(sr.findById(mockSeller.getId())).thenReturn(Optional.of(mockSeller));
        Assertions.assertEquals(mockSeller, mockSellerService.getSellerById(mockSeller.getId()).get());
    }

    @Test
    void getAllSellers() {
        Mockito.when(sr.findAll()).thenReturn(sellers);
        Assertions.assertEquals(sellers, mockSellerService.getAllSellers());
    }

    @Test
    void deleteSeller() {
        Assertions.assertTrue(mockSellerService.deleteSeller(mockSeller.getId()));
        Mockito.doThrow(new IllegalArgumentException()).when(sr).deleteById(0);
        Assertions.assertFalse(mockSellerService.deleteSeller(0));
    }

    @Test
    void getSellerByUserId() {
        Mockito.when(sr.findByUserId(mockUser.getId())).thenReturn(Optional.of(mockSeller));
        Assertions.assertEquals(mockSeller, mockSellerService.getSellerByUserId(mockUser.getId()).get());
    }
}
