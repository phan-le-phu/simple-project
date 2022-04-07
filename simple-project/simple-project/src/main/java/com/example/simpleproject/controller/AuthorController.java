package com.example.simpleproject.controller;

import com.example.simpleproject.dto.AuthorDto;
import com.example.simpleproject.service.AuthorService;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;

@RestController
@RequestMapping("/author")
public class AuthorController {

    private final AuthorService authorService;

    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @PostMapping
    public void insertAuthor(@RequestBody AuthorDto author) throws ParseException {
        authorService.insertAuthor(author);
    }

    @GetMapping
    public List<AuthorDto> getAuthors() {
        List<AuthorDto> authorDtos = authorService.findAllAuthor();
        return authorDtos;
    }
}
