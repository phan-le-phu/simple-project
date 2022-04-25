package com.example.simpleproject.controller;

import com.example.simpleproject.dto.BookDto;
import com.example.simpleproject.service.BookService;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.security.RolesAllowed;

@RestController
@RequestMapping("/book")
public class BookController {
    private final BookService bookService;

    private static final Logger LOGGER = LogManager.getLogger(BookController.class.getName());

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping
    public ModelAndView getBooks() {

        ModelAndView modelAndView = new ModelAndView("/book/view");
        modelAndView.addObject("books", bookService.getAllBook());
        return modelAndView;
    }

    @RolesAllowed("ADMIN")
    @PostMapping
    public void insertBookForAuthor(@RequestBody BookDto bookDto){
        bookService.insertBookForAuthor(bookDto);

        LOGGER.log(Level.INFO, "A new book " + bookDto.toString() + " has been inserted to the database");
    }
}
