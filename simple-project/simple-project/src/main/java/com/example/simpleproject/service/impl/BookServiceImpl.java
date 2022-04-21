package com.example.simpleproject.service.impl;

import com.example.simpleproject.converter.BookConverter;
import com.example.simpleproject.dto.BookDto;
import com.example.simpleproject.model.Author;
import com.example.simpleproject.model.Book;
import com.example.simpleproject.repository.AuthorRepository;
import com.example.simpleproject.repository.BookRepository;
import com.example.simpleproject.service.BookService;
import lombok.Data;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;
    private final BookConverter bookConverter;



    public BookServiceImpl(BookRepository bookRepository,
                           AuthorRepository authorRepository,
                           BookConverter bookConverter) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
        this.bookConverter = bookConverter;
    }



    public List<BookDto> getAllBook() {
        List<Book> books = bookRepository.findAll();
        
        List<BookDto> bookDtos = books.stream().map(e -> bookConverter.toBookDto(e)).collect(Collectors.toList());
        return bookDtos;
    }

    public void insertBookForAuthor(BookDto bookDto) {
        Optional<Author> authorOptional = authorRepository.findById(bookDto.getAuthorId());

        if (!authorOptional.isPresent()) {
            throw new IllegalArgumentException("Author id does not exist");
        }

        Book book = bookConverter.toBook(bookDto);
        book.setAuthor(authorOptional.get());
        bookRepository.save(book);

    }
}
