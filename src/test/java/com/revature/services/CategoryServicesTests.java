package com.revature.services;

import com.revature.models.*;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@SpringBootTest
public class CategoryServicesTests {

    @Autowired
    CategoryService categoryService;

    @MockBean
    private CategoryRepository categoryRepository;


    @Test
    public void testGetCategoryById() {
        Category category = categoryService.getCategoryById();
        Assertions.assertEquals(1, category.getId());
        Assertions.assertEquals("Food", category.getName());
    }
}
