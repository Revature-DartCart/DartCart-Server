package com.revature.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.models.*;
import com.revature.repositories.InvoiceRepo;
import com.revature.services.InvoiceService;
import com.revature.services.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
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


class InvoiceControllerTest {
    ObjectMapper mapper = new ObjectMapper();
    private MockMvc mvc;

    @MockBean
    private InvoiceService mockInvoiceService;

    @Autowired
    private WebApplicationContext webApplicationContext;
   @Autowired
   private InvoiceRepo ir;
    @BeforeEach
    void setup() {
        mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
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
            (List<OrderDetail>) mockOrderDetails
    );




    @Test
    void getInvoiceByCustomerId() throws Exception {
        List<Invoice> customerInvoiceList = new ArrayList<>();
        customerInvoiceList = mockInvoiceService.getInvoiceByCustomerId(1);
       // int customerId = mockInvoiceService.getInvoiceByCustomerId(1);
        Mockito.when(mockInvoiceService.getInvoiceByCustomerId(mockUser.getId())).thenReturn(customerInvoiceList);
        ResultActions ra = mvc.perform(MockMvcRequestBuilders.get("/invoices/customer/1"));
        ra.andExpect(MockMvcResultMatchers.status().isOk());
    }
}