package com.revature.services;

import com.revature.driver.DartCartApplication;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK, classes = DartCartApplication.class)
public class CheckoutServiceTest {

    @Test
    public void testUserCheckoutWithValidCart() {}

    @Test
    public void testUserCheckoutWithInvalidCart() {}

}
