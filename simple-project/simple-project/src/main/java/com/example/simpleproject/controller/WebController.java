package com.example.simpleproject.controller;

import com.example.simpleproject.service.AuthorService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class WebController {

    private final AuthorService authorService;

    public WebController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @RequestMapping(value = "/")
    public String index() {
       return "index";
    }

    @RequestMapping(value = "/author/form")
    public String authorForm() {
        return "author/form";
    }

    @RequestMapping(value = "/book/form")
    public ModelAndView bookForm() {
        ModelAndView modelAndView = new ModelAndView("/book/form");
        modelAndView.addObject("authors", authorService.findAllAuthor());
        return modelAndView;
    }

}