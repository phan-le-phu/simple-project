package com.example.simpleproject.dao;

import com.example.simpleproject.model.Book;

import java.util.List;

public interface BookDao {

    Book save(Book book);

    List<Book> findAll();

    Book findByName(final String name);

    Book findById(final Long id);

    List<Book> saveAll(final List<Book> books);


}
