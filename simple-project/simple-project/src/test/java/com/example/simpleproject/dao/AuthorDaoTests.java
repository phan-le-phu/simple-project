package com.example.simpleproject.dao;

import com.example.simpleproject.model.Author;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.modelmapper.internal.util.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.event.annotation.BeforeTestClass;
import org.springframework.test.context.event.annotation.BeforeTestMethod;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@SpringBootTest
public class AuthorDaoTests {

    @Autowired
    AuthorDao authorDao;

    private static List<Author> authors = new ArrayList<>();

    @BeforeAll
    public static void initDatabase() {
        authors.add(new Author("Phu", new Date()));
        authors.add(new Author( "Tuan", new Date()));
    }

    @Test
    public void InsertMultipleAuthorsTest() {
        authorDao.saveAll(authors);

        Assert.isTrue(authors.size() == authorDao.findAll().size());
    }

    @Test
    public void InsertAndThenFindAuthorByNameTest() {

        Author author = authorDao.findByName("Phu");

        Assert.isTrue(author != null);
    }

    @Test
    public void InsertAndThenFindAuthorByIdTest() {
        Author author = authorDao.findById(1L);

        Assert.isTrue(author != null);
    }
}
