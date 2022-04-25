package com.example.simpleproject.dao.impl;

import com.example.simpleproject.dao.BookDao;
import com.example.simpleproject.model.Book;
import com.example.simpleproject.model.QBook;
import com.querydsl.jpa.impl.JPAQuery;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Repository
public class BookDaoImpl implements BookDao {

    @PersistenceContext
    EntityManager em;

    @Override
    @Transactional
    public Book save(final Book book) {
        em.persist(book);

        return book;
    }

    @Override
    public Book findById(final Long id) {
        JPAQuery<Book> query = new JPAQuery(em);
        QBook book = QBook.book;

        return query.from(book).where(book.id.eq(id)).fetchOne();
    }

    @Override
    public Book findByName(final String name) {
        JPAQuery<Book> query = new JPAQuery<>(em);
        QBook book = QBook.book;

        return query.from(book).where(book.name.eq(name)).fetchOne();
    }

    @Override
    public List<Book> findAll() {
        JPAQuery<Book> query = new JPAQuery<>(em);
        QBook book = QBook.book;

        return  query.from(book).fetch();
    }

    @Transactional
    @Override
    public List<Book> saveAll(List<Book> books) {

        books.stream().forEach(this::save);

        return books;
    }
}
