package com.revature.services;



import com.revature.driver.DartCartApplication;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.security.core.userdetails.UserDetails;


import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest(classes = DartCartApplication.class)
public class AuthServiceTest {


    private final String trueUser = "test";
    private final String falseUser = "fakeUser";


    //class where method exist
    private static Object authService;

    private static final String CLASSNAME = "com.revature.services.AuthService";

    private static final String METHOD = "loadUserByUsername";

    //method we are testing
    private static Method loadUser;


    @BeforeAll
    static void setUp(@Autowired ApplicationContext app) {
        try{
        authService = app.getBean(Class.forName(CLASSNAME));
        } catch(ClassNotFoundException e) {
            throw new RuntimeException("Class: " + CLASSNAME + " needs to be implemented", e);
        }

        try {
            loadUser = authService.getClass().getMethod(METHOD, String.class);
        } catch (NoSuchMethodException e) {
            throw new RuntimeException("Method: " + METHOD + " needs to be implemented", e);
        }

    }

    @Test
    void valid_GetUsernameByUsername() throws InvocationTargetException, IllegalAccessException {
        UserDetails retVal = (UserDetails) loadUser.invoke(authService, trueUser);
        assertEquals(trueUser, retVal.getUsername());
    }

    @Test
    void invalid_GetUsernameByUsername() throws InvocationTargetException, IllegalAccessException {
        assertThrows(InvocationTargetException.class, () -> loadUser.invoke(authService, falseUser));
    }

}
