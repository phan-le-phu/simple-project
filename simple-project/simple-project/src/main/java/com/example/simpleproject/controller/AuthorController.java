package com.example.simpleproject.controller;

import com.example.simpleproject.dto.AuthorDto;
import com.example.simpleproject.service.AuthorService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.text.ParseException;

@RestController
@RequestMapping("/author")
public class AuthorController {

    private final AuthorService authorService;

    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @PostMapping
    public void insertAuthor(@RequestBody AuthorDto authorDto) throws ParseException {
        authorService.insertAuthor(authorDto);
    }

    @GetMapping
    public ModelAndView view(){
        ModelAndView modelAndView = new ModelAndView("author/view");
        modelAndView.addObject("authors", authorService.findAllAuthor());
        return modelAndView;
    }

}
