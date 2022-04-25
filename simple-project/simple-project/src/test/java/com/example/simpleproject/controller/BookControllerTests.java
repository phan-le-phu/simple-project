package com.example.simpleproject.controller;

import com.example.simpleproject.dto.BookDto;
import com.example.simpleproject.service.BookService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.util.HashMap;
import java.util.Map;

import static org.mockito.Mockito.doNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@SpringBootTest
@AutoConfigureMockMvc
public class BookControllerTests {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    BookService bookService;


    @WithMockUser(username = "user1", password = "user1Pass")
    @Test
    public void givenUrlBookView_whenRequestViewBooks()

        throws Exception {

        mockMvc.perform(get("/book"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("books"))
                .andExpect(view().name("/book/view"));
    }

    @Test
    @WithMockUser(username = "user1", password = "user1Pass")
    public void postRequest_InsertBook_ByUserHasRoleUser()
        throws Exception{
        System.out.println(jsonBookDto());
        mockMvc.perform(post("/book")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonBookDto()))
                .andExpect(status().isForbidden());

    }

    @Test
    @WithMockUser(username = "admin", password = "adminPass", roles = "ADMIN")
    public void postRequest_InsertBook_ByAdmin() throws Exception {

        doNothing().when(bookService).insertBookForAuthor(new BookDto());

        mockMvc.perform(post("/book")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonBookDto()))
                .andExpect(status().isOk());
    }

    private String jsonBookDto() throws Exception {
       Map<String, Object> body = new HashMap<>();
       body.put("name", "Giet con chim nhai");
       body.put("authorId", 1L);
       body.put("authorName", "phu");

       ObjectMapper objectMapper = new ObjectMapper();

       return objectMapper.writeValueAsString(body);
    }
}
