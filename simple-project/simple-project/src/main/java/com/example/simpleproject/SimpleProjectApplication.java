package com.example.simpleproject;

import com.example.simpleproject.model.Author;
import com.example.simpleproject.model.Book;
import com.example.simpleproject.repository.AuthorRepository;
import com.example.simpleproject.repository.BookRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;
import java.util.Date;

@SpringBootApplication
public class SimpleProjectApplication {

	@Autowired
	AuthorRepository authorRepository;

	@Autowired
	BookRepository bookRepository;


	public static void main(String[] args) {
		SpringApplication.run(SimpleProjectApplication.class, args);
	}


}
