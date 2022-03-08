package com.revature.services;

import com.revature.driver.DartCartApplication;
import com.revature.models.*;
import com.revature.repositories.InvoiceRepo;
import com.revature.repositories.UserRepo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.List;
import java.util.Optional;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK, classes = DartCartApplication.class)
class InvoiceServiceTest {

    @Autowired
    private InvoiceRepo mockInvoiceRepo;
    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private UserRepo mockUserRepo;


    @Autowired
    private UserService mockUserService;


    @MockBean
    private InvoiceService is;

    @Autowired
    BCryptPasswordEncoder bCryptEncoder;

    List<OrderDetail> od;

    @BeforeEach
    void setup() {
        MockMvc mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    final private User mockUser = new User(
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
    final private Seller mockSeller = new Seller(
            1,
            "spiderman",
            "whatspopin",
            "seller implementation",
            mockUser


            );
    final private Shop mockShop = new Shop(
            1,
            "Denver Colorado",
            mockSeller


    );
    final private OrderDetails mockOrderDetails = new OrderDetails(
            1,
            9,
            "iron man",
            "peter",
            8


            );
    final private Invoice mockinvoice = new Invoice(
            1,
            03072022,
            "canada",
            "mexico",
            mockUser,
            mockShop,
           od

    );


    @Test
    void getInvoiceByCustomerId() {
        User u = mockUser;
        List<Invoice> customerInvoiceList = is.getInvoiceByCustomerId(u.getId());
        Mockito.when(is.getInvoiceByCustomerId(mockinvoice.getCustomer().getId())).thenReturn(customerInvoiceList);
        Invoice actual = is.getAllInvoices().get(u.getId());
        Assertions.assertEquals(actual.getId(), u.getId());
    }
}