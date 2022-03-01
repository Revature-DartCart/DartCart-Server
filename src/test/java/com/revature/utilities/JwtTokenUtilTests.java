package com.revature.utilities;

import com.revature.app.DartCartApplication;
import com.revature.repositories.UserRepo;
import com.revature.utilities.JwtTokenUtil;
import org.mockito.Mockito;
import org.springframework.context.ApplicationContext;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.ClassOrderer;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.web.servlet.MockMvc;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import static org.junit.jupiter.api.Assertions.assertFalse;

@SpringBootTest(classes = DartCartApplication.class)
public class JwtTokenUtilTests {
    
    @Autowired
    private ApplicationContext app;
    private static Object tokenUtility;
    private static String CLASSNAME = "com.revature.utilities.JwtTokenUtil";

    private static final String VALIDATE = "validate";
    private static Method validate;



    /*
    * Tests the validation of JWTTokens
    * */
    @BeforeAll
    static void testingSetup(@Autowired ApplicationContext app) {



    }




}
