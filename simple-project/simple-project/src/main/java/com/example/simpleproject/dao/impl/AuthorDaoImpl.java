package com.example.simpleproject.dao.impl;

import com.example.simpleproject.dao.AuthorDao;
import com.example.simpleproject.model.Author;
import com.example.simpleproject.model.QAuthor;
import com.querydsl.jpa.impl.JPAQuery;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Repository
public class AuthorDaoImpl implements AuthorDao {

    @PersistenceContext
    private EntityManager em;

    @Override
    @Transactional
    public  Author save(Author author) {
       em.persist(author);
       return author;
    }

    @Override
    @Transactional
    public List<Author> saveAll(List<Author> authors) {

        authors.stream().forEach(this::save);

        return authors;
    }

    @Override
    public Author findByName(final String name) {
        final JPAQuery<Author> query = new JPAQuery<>(em);
        final QAuthor author = QAuthor.author;

        return query.from(author).where(author.name.eq(name)).fetchOne();
    }

    @Override
    public Author findById(final Long id) {
        final JPAQuery<Author> query = new JPAQuery<>(em);
        final QAuthor author = QAuthor.author;

        return query.from(author).where(author.id.eq(id)).fetchOne();
    }

    @Override
    public List<Author> findAll() {
        final JPAQuery<Author> query = new JPAQuery<>(em);
        final QAuthor author = QAuthor.author;

        return query.from(author).fetch();
    }

}
