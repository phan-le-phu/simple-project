package com.example.simpleproject.service.impl;

import com.example.simpleproject.converter.BookConverter;
import com.example.simpleproject.dao.AuthorDao;
import com.example.simpleproject.dao.BookDao;
import com.example.simpleproject.dto.BookDto;
import com.example.simpleproject.model.Author;
import com.example.simpleproject.model.Book;
import com.example.simpleproject.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private AuthorDao authorDao;

    @Autowired
    private BookDao bookDao;

    @Autowired
    private BookConverter bookConverter;


    public List<BookDto> getAllBook() {
        List<Book> books = bookDao.findAll();
        
        List<BookDto> bookDtos = books.stream().map(e -> bookConverter.toBookDto(e)).collect(Collectors.toList());
        return bookDtos;
    }

    public void insertBookForAuthor(BookDto bookDto) {
        Optional<Author> authorOptional = Optional.ofNullable(authorDao.findById(bookDto.getAuthorId()));

        if (!authorOptional.isPresent()) {
            throw new IllegalArgumentException("Author id does not exist");
        }

        Book book = bookConverter.toBook(bookDto);
        book.setAuthor(authorOptional.get());
        bookDao.save(book);

    }
}
