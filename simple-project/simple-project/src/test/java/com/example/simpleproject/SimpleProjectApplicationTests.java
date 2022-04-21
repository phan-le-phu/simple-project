package com.example.simpleproject;

import com.example.simpleproject.controller.AuthorController;
import com.example.simpleproject.controller.BookController;
import com.example.simpleproject.controller.WebController;
import org.junit.jupiter.api.Test;
import org.modelmapper.internal.util.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;

@SpringBootTest
public class SimpleProjectApplicationTests {

    @Autowired
    AuthorController authorController;

    @Autowired
    BookController bookController;

    @Autowired
    WebController webController;

    @Test
    public void contextLoads() {
        Assert.notNull(authorController);
        Assert.notNull(bookController);
        Assert.notNull(webController);
    }
}
