package com.example.simpleproject.controller;

import com.example.simpleproject.dao.AuthorDao;
import com.example.simpleproject.model.Author;
import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@WithMockUser(username = "admin", password = "adminPass", roles = {"ADMIN"})
public class WebControllerTests {

    @Autowired
    private MockMvc  mockMvc;

    @Autowired
    private AuthorDao authorDao;

    @Test
    public void givenHomepageUri_whenGetHomepage_thenReturnHtmlDocument() throws Exception {
        MvcResult mvcResult = mockMvc.perform(get("/index")
                        .contentType(MediaType.TEXT_HTML))
                        .andExpect(view().name("index"))
                        .andExpect(status().isOk()).andReturn();

        assertEquals(mvcResult.getResponse().getHeader("Content-Type"), "text/html;charset=UTF-8");
    }

    @Test
    public void givenAuthorFormUri_whenGetAuthorForm_thenReturnFormDocument() throws Exception {
        MvcResult mvcResult = mockMvc.perform(get("/admin/author/form"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("author/form"))
                .andReturn();

        assertEquals(mvcResult.getResponse().getHeader("Content-Type"),
                "text/html;charset=UTF-8");
    }

    @Test
    public void givenBookFormUri_whenGetBookForm_thenReturnFormDocument()
        throws Exception {


        MvcResult mvcResult = mockMvc.perform(get("/admin/book/form"))
                .andExpect(status().isOk())
                .andExpect(view().name("/book/form"))
                .andExpect(model().attributeExists("authors"))
                .andDo(print())
                .andReturn();

        assertEquals(mvcResult.getResponse().getHeader("Content-type"),
                "text/html;charset=UTF-8");

    }

    @Test
    public void givenLoginUri_whenGetLoginPage_thenReturnLoginFormDocument()
        throws Exception {

        MvcResult mvcResult = mockMvc.perform(get("/login"))
                .andExpect(status().isOk())
                .andExpect(view().name("login.html"))
                .andReturn();

        assertEquals(mvcResult.getResponse().getHeader("Content-type"),
                "text/html;charset=UTF-8");
    }
}
