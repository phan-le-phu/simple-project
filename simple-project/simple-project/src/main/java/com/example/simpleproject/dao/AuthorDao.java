package com.example.simpleproject.dao;

import com.example.simpleproject.model.Author;

import java.util.List;

public interface AuthorDao {

    Author save(final Author author);

    List<Author> findAll();

    Author findByName(final String name);

    Author findById(final Long id);

    List<Author> saveAll(List<Author> authors);

}
