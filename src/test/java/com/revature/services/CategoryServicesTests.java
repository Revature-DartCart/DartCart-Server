package com.revature.services;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@SpringBootTest(classes = {})
public class CategoryServicesTests {

    @Autowired
    CategoryService cs;

    @MockBean
    private CategoryRepo cp;


    



}
