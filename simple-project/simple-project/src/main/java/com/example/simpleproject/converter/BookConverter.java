package com.example.simpleproject.converter;

import com.example.simpleproject.dto.BookDto;
import com.example.simpleproject.model.Book;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BookConverter {
    @Autowired
    private ModelMapper modelMapper;

    public Book toBook(BookDto bookDto) {
        Book book = modelMapper.map(bookDto, Book.class);
        return book;
    }

    public BookDto toBookDto(Book book) {
        BookDto bookDto = modelMapper.map(book, BookDto.class);
        bookDto.setAuthorName(book.getAuthor().getName());
        return bookDto;
    }

}
