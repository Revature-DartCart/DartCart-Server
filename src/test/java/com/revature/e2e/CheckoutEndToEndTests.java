package com.revature.e2e;

import com.revature.driver.DartCartApplication;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK, classes = DartCartApplication.class)
public class CheckoutEndToEndTests {

    @Test
    public void testUnauthorizedUser() {}

    @Test
    public void testUserCheckoutWithValidCart() {}

    @Test
    public void testUserCheckoutWithInvalidCart() {}
}
