package com.example.simpleproject.controller;

import com.example.simpleproject.dto.BookDto;
import com.example.simpleproject.service.BookService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("/book")
public class BookController {
    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping
    public ModelAndView getBooks() {
        ModelAndView modelAndView = new ModelAndView("/book/view");
        modelAndView.addObject("books", bookService.getAllBook());
        return modelAndView;
    }

    @PostMapping
    public void insertBookForAuthor(@RequestBody BookDto bookDto){
        bookService.insertBookForAuthor(bookDto);
    }
}
