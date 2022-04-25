package com.example.simpleproject.service;

import com.example.simpleproject.converter.BookConverter;
import com.example.simpleproject.dto.BookDto;
import com.example.simpleproject.model.Author;
import com.example.simpleproject.model.Book;
import com.example.simpleproject.repository.AuthorRepository;
import com.example.simpleproject.repository.BookRepository;
import org.junit.jupiter.api.Test;
import org.modelmapper.internal.util.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@SpringBootTest
public class BookServiceTests {

    @Autowired
    BookService bookService;

    @MockBean
    BookRepository bookRepository;

    @MockBean
    AuthorRepository authorRepository;

    @Autowired
    BookConverter bookConverter;

    @Test
    public void testFindAllBooks() {
        Author author = new Author(1L, "phu", new Date());
        List<Book> bookList = new ArrayList<>();
        bookList.add(new Book(1L, "hoang tu be", author));
        bookList.add(new Book(2L, "giet con chim nhai", author));

        when(bookRepository.findAll()).thenReturn(bookList);

        Assert.isTrue(bookService.getAllBook().size() == 2);

        verify(bookRepository, times(1)).findAll();

    }

    @Test
    public void testInsertBookForAuthorExceptionCase() {

        Long authorId = 1L;
        BookDto bookDto = new BookDto("giet con chim nhai", authorId, "phu");

        when(authorRepository.findById(authorId)).thenReturn(Optional.empty());

        try {
            bookService.insertBookForAuthor(bookDto);
        } catch (Exception ex) {
            Assert.isTrue(ex.getMessage() == "Author id does not exist");
        }

        verify(authorRepository, times(1)).findById(authorId);

    }

    @Test
    public void testInsertBookForAuthorValidCase() {

        BookDto bookDto = new BookDto("giet con chim nhai", 1L, "phu");
        Long authorId = 1L;
        Book book = bookConverter.toBook(bookDto);

        when(authorRepository.findById(authorId)).thenReturn(Optional.of(book.getAuthor()));

        when(bookRepository.save(book)).thenReturn(book);

        bookService.insertBookForAuthor(bookDto);

        verify(authorRepository, times(1)).findById(authorId);
        verify(bookRepository, times(1)).save(book);
    }
}
