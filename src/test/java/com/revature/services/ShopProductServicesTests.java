package com.revature.services;

import com.revature.dartcart.DartCartApplication;
import com.revature.models.Category;
import com.revature.models.Product;
import com.revature.models.ShopProduct;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest(classes = DartCartApplication.class)
public class ShopProductServicesTests {
    @Autowired
    private ShopProductService ShopProductService;

    @MockBean
    private ShopProductRepository ShopProductRepo;

    final static ShopProduct testShopProduct = new ShopProduct(
            1,
            new Product(
                    1,
                    "testProduct",
                    "testDescription",
                    new ArrayList<Category>(Arrays.asList(new Category(1, "testCategory")))),
            20,
            50.0,
            0.0
    );

    @Test
    void getShopProductById(int id) {
        when(ShopProductRepo.findById(1)).thenReturn(Optional.of(testShopProduct));

        Optional<ShopProduct> shopProduct = shopProductService.getShopProductById(id);

        assertTrue(shopProduct.isPresent());

        assertEquals(shopProduct.get().getId(), 1);
        assertNotEquals(shopProduct.get().getId(), 2);

        assertEquals(shopProduct.get().getQuantity(), 20);
        assertNotEquals(shopProduct.get().getQuantity(), 50);

        assertEquals(shopProduct.get().getPrice(), 50.0);
        assertNotEquals(shopProduct.get().getPrice(), 100.0);

        assertEquals(shopProduct.get().getDiscount(), 0.0);
        assertNotEquals(shopProduct.get().getDiscount(), 10.0);

        assertEquals(shopProduct.get().getProduct().getId(), 1);
        assertNotEquals(shopProduct.get().getProduct().getId(), 2);

        assertEquals(shopProduct.get().getProduct().getName(), "testProduct");
        assertNotEquals(shopProduct.get().getProduct().getName(), "notTestProduct");

        assertEquals(shopProduct.get().getProduct().getDescription(), "testDescription");
        assertNotEquals(shopProduct.get().getProduct().getDescription(), "notTestDescription");

        assertEquals(shopProduct.get().getProduct().getCategoryList().size(), 1);
        assertNotEquals(shopProduct.get().getProduct().getCategoryList().size(), 0);
    }

}
