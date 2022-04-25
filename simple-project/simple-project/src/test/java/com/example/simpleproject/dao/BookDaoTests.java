package com.example.simpleproject.dao;

import com.example.simpleproject.model.Author;
import com.example.simpleproject.model.Book;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@SpringBootTest
public class BookDaoTests {

    @Autowired
    private BookDao bookDao;

    @Autowired
    private  AuthorDao authorDao;

    private static List<Book> books = new ArrayList<>();

    private static Author author = new Author("Huy", new Date());


    @Test
    public void findBookByNameTest() {

        authorDao.save(author);

        Author author = authorDao.findByName("Huy");
        books.add(new Book("Giet con chim nhai", author));
        books.add(new Book("Tieng chim hot trong bui man gai", author));

        bookDao.saveAll(books);


        Book book = bookDao.findByName("Giet con chim nhai");

        Assert.notNull(book, "book object not found");
    }

    @Test
    public void findBookByIdTest() {

        Book book = bookDao.findById(1L);

        Assert.notNull(book, "book object not found");
    }


}
