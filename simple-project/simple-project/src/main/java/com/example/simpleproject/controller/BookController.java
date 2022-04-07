package com.example.simpleproject.controller;

import com.example.simpleproject.dto.BookDto;
import com.example.simpleproject.service.BookService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/book")
public class BookController {
    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping
    public List<BookDto> getBooks() {
        List<BookDto> bookDtos = bookService.getAllBook();
        return bookDtos;
    }

    @PostMapping("/{id}")
    public void insertBookForAuthor(@PathVariable("id") Long authorId, @RequestBody List<BookDto> listBookDtos){
        bookService.insertBookForAuthor(authorId, listBookDtos);
    }
}
