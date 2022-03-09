package com.revature.services;

import com.revature.driver.DartCartApplication;
import com.revature.exceptions.BadTransactionException;
import com.revature.models.*;
import com.revature.repositories.CartItemRepository;
import com.revature.repositories.InvoiceRepository;
import com.revature.repositories.UserRepo;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK, classes = DartCartApplication.class)
public class CheckoutServiceTest {

    @Autowired
    private CheckoutService checkoutService;

    @MockBean
    private UserRepo userRepo;

    @MockBean
    private InvoiceRepository invoiceRepository;

    @MockBean
    private CartItemRepository cartItemRepository;

    @Test
    public void testUserCheckoutWithValidCart() throws BadTransactionException {
        User user = new User(
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

        Shop shop = new Shop(1, "blah", null);
        Product product = new Product(1, "test product", "test description", null);
        Product product2 = new Product(2, "test2 product", "test2 description", null);
        ShopProduct shopProduct = new ShopProduct(1, 5, 100, 100, shop, product);
        ShopProduct shopProduct2 = new ShopProduct(2, 5, 100, 100, shop, product2);

        // add items to cart
        CartItem cartItem = new CartItem(1, 3, false, user, shopProduct);
        CartItem cartItem2 = new CartItem(1, 3, false, user, shopProduct2);
        user.getItemList().add(cartItem);
        user.getItemList().add(cartItem2);

        List<OrderDetail> orderDetails = new ArrayList<>();
        orderDetails.add(new OrderDetail(1, 100, "test product", "test description", 3));
        orderDetails.add(new OrderDetail(1, 100, "test2 product", "test2 description", 3));
        Invoice invoice = new Invoice(1, System.currentTimeMillis(), shop.getLocation(), user.getLocation(), user, orderDetails, shop);

        // get current cart for confirmation
        when(userRepo.findById(user.getId())).thenReturn(Optional.of(user));
        when(invoiceRepository.save(Mockito.any(Invoice.class))).thenReturn(invoice);

        user = checkoutService.checkout(user);

        verify(invoiceRepository, times(1)).save(Mockito.any(Invoice.class));
        verify(cartItemRepository, times(1)).deleteAll(Mockito.any(List.class));

        assertEquals(0, user.getItemList().size());
    }

    @Test
    public void testUserCheckoutWithInvalidCart() throws BadTransactionException {
        User user = new User(
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

        User realUser = new User(
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

        Shop shop = new Shop(1, "blah", null);
        Product product = new Product(1, "test product", "test description", new ArrayList<>());
        product.getCategories().add(new Category(1, "Food"));
        ShopProduct shopProduct = new ShopProduct(1, 5, 100, 100, shop, product);

        // add items to cart
        CartItem cartItem = new CartItem(1, 5, false, user, shopProduct);
        realUser.getItemList().add(cartItem);

        // get current cart for confirmation
        when(userRepo.findById(user.getId())).thenReturn(Optional.of(realUser));

        user = checkoutService.checkout(user);

        assertEquals(1, user.getItemList().size());
        assertEquals(cartItem.getId(), user.getItemList().get(0).getId());
    }

    @Test
    public void testMissingUser() throws BadTransactionException {
        User user = new User(
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

        when(userRepo.findById(user.getId())).thenReturn(Optional.empty());

        User u = checkoutService.checkout(user);

        assertEquals(user, u);
    }

    @Test
    public void testBadTransaction() {
        User user = new User(
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

        Shop shop = new Shop(1, "blah", null);
        Product product = new Product(1, "test product", "test description", null);
        Product product2 = new Product(2, "test2 product", "test2 description", null);
        ShopProduct shopProduct = new ShopProduct(1, 5, 100, 100, shop, product);
        ShopProduct shopProduct2 = new ShopProduct(2, 5, 100, 100, shop, product2);

        // add items to cart
        CartItem cartItem = new CartItem(1, 300, false, user, shopProduct);
        CartItem cartItem2 = new CartItem(1, 3, false, user, shopProduct2);
        user.getItemList().add(cartItem);
        user.getItemList().add(cartItem2);

        List<OrderDetail> orderDetails = new ArrayList<>();
        orderDetails.add(new OrderDetail(1, 100, "test product", "test description", 3));
        orderDetails.add(new OrderDetail(1, 100, "test2 product", "test2 description", 3));
        Invoice invoice = new Invoice(1, System.currentTimeMillis(), shop.getLocation(), user.getLocation(), user, orderDetails, shop);

        // get current cart for confirmation
        when(userRepo.findById(user.getId())).thenReturn(Optional.of(user));
        when(invoiceRepository.save(Mockito.any(Invoice.class))).thenReturn(invoice);

        assertThrows(BadTransactionException.class, () -> checkoutService.checkout(user));
    }
}
