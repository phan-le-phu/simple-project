package com.example.simpleproject.service;

import com.example.simpleproject.dto.BookDto;

import java.util.List;

public interface BookService {
    List<BookDto> getAllBook();
    void insertBookForAuthor(BookDto bookDto);
}
