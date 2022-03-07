package com.revature.unit.services;

import com.revature.driver.DartCartApplication;
import com.revature.models.Category;
import com.revature.models.Product;
import com.revature.models.Shop;
import com.revature.models.ShopProduct;
import com.revature.services.ShopProductService;
import com.revature.services.ShopProductServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.revature.repositories.ShopProductRepo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest(classes = DartCartApplication.class)
public class ShopProductServicesTests {
    @Autowired
    private ShopProductService shopProductService;

    @MockBean
    private ShopProductRepo shopProductRepo;

    final static private Shop shop = new Shop();


    final static ShopProduct testShopProduct = new ShopProduct(
            1,
            20,
            50,
            0,
            shop,
            new Product(
                    1,
                    "testProduct",
                    "testDescription",
                    new ArrayList<Category>(Arrays.asList(new Category(1, "testCategory"))))
    );

    final static ShopProduct testShopProduct2 = new ShopProduct(
            1,
            30,
            70,
            2,
            shop,
            new Product(2,
                    "testProduct2",
                    "testDescription2",
                    new ArrayList<Category>(Arrays.asList(new Category(2, "testCategory2"))))
    );

    final static ShopProduct testShopProduct3 = new ShopProduct(
            1,
            90,
            10,
            5,
            shop,
            new Product(3,
                    "testProduct3",
                    "testDescription3",
                    new ArrayList<Category>(Arrays.asList(new Category(3, "testCategory3"))))
    );

    @Test
    void getShopProductByIdPass() {
        when(shopProductRepo.findById(1)).thenReturn(Optional.of(testShopProduct));
        Optional<ShopProduct> shopProduct = shopProductService.getShopProductById(1);

        assertTrue(shopProduct.isPresent());

        assertEquals(shopProduct.get().getId(), 1);
        assertEquals(shopProduct.get().getQuantity(), 20);
        assertEquals(shopProduct.get().getPrice(), 50);
        assertEquals(shopProduct.get().getDiscount(), 0);
        assertEquals(shopProduct.get().getProduct().getId(), 1);
        assertEquals(shopProduct.get().getProduct().getName(), "testProduct");
        assertEquals(shopProduct.get().getProduct().getDescription(), "testDescription");
        assertEquals(shopProduct.get().getProduct().getCategories().size(), 1);
    }

    @Test
    void getShopProductByIdFail() {
        when(shopProductRepo.findById(1)).thenReturn(Optional.of(testShopProduct));
        Optional<ShopProduct> shopProduct = shopProductService.getShopProductById(1);

        assertTrue(shopProduct.isPresent());

        assertNotEquals(shopProduct.get().getId(), 2);
        assertNotEquals(shopProduct.get().getQuantity(), 50);
        assertNotEquals(shopProduct.get().getPrice(), 100);
        assertNotEquals(shopProduct.get().getDiscount(), 10);
        assertNotEquals(shopProduct.get().getProduct().getId(), 2);
        assertNotEquals(shopProduct.get().getProduct().getName(), "notTestProduct");
        assertNotEquals(shopProduct.get().getProduct().getDescription(), "notTestDescription");
        assertNotEquals(shopProduct.get().getProduct().getCategories().size(), 0);
    }

    @Test
    void getAllShopProductsPass() {
        List<ShopProduct> testList = new ArrayList<>();
        testList.add(testShopProduct);
        testList.add(testShopProduct2);
        testList.add(testShopProduct3);

        when(shopProductRepo.findAll()).thenReturn(testList);
        List<ShopProduct> shopProducts = shopProductService.getAllShopProducts();

        assertEquals(shopProducts.size(), 3);
    }

    @Test
    void getAllShopProductsFail() {
        List<ShopProduct> testList = new ArrayList<>();
        testList.add(testShopProduct);
        testList.add(testShopProduct2);
        testList.add(testShopProduct3);

        when(shopProductRepo.findAll()).thenReturn(testList);
        List<ShopProduct> shopProducts = shopProductService.getAllShopProducts();

        assertNotEquals(shopProducts.size(), 2);
    }

    @Test
    void searchShopProducts() {
        List<ShopProduct> testList = new ArrayList<>();
        ShopProduct shopProduct = new ShopProduct();
        Category category = new Category(1, "Food");
        List<Category> categories = new ArrayList<>();
        categories.add(category);
        Product product = new Product(1, "Kelloggs Froot Loops", "Delicious frooty flava",categories );
        shopProduct.setProduct(product);
        shopProduct.setQuantity(10);
        shopProduct.setDiscount(2);
        shopProduct.setPrice(15);
        testList.add(shopProduct);

        when(shopProductRepo.findAll()).thenReturn(testList);
        List<ShopProduct> shopProductList = shopProductService.searchByProductName("Froo");
        assertEquals(shopProductList.size(), 1);

        assertEquals(testList.get(0).getProduct().getName(), shopProductList.get(0).getProduct().getName());
    }

    @Test
    void searchShopProductsFail() {
        List<ShopProduct> testList = new ArrayList<>();
        ShopProduct shopProduct = new ShopProduct();
        Category category = new Category(1, "Food");
        List<Category> categories = new ArrayList<>();
        categories.add(category);
        Product product = new Product(1, "Loops", "Delicious frooty flava",categories );
        shopProduct.setProduct(product);
        shopProduct.setQuantity(10);
        shopProduct.setDiscount(2);
        shopProduct.setPrice(15);
        testList.add(shopProduct);

        when(shopProductRepo.findAll()).thenReturn(testList);
        List<ShopProduct> shopProductList = shopProductService.searchByProductName("Froo");

        assertNotEquals(testList.size(), shopProductList.size());
    }

}
