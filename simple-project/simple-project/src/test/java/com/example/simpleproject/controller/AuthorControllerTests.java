package com.example.simpleproject.controller;

import com.example.simpleproject.dto.AuthorDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.web.header.Header;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.ui.Model;

import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class AuthorControllerTests {

    @Autowired
    AuthorController authorController;

    @Autowired
    MockMvc mockMvc;

    public static final MediaType APPLICATION_JSON_UTF8 = new MediaType(MediaType.APPLICATION_JSON.getType(), MediaType.APPLICATION_JSON.getSubtype(), Charset.forName("utf8"));


    @WithMockUser(value = "user1")
    @Test
    public void givenPostUriWithByNormalUser_whenInsertAuthor()
        throws Exception {
            mockMvc.perform(post("/author")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(responseBody()))
                    .andExpect(status().isForbidden());
    }

    @WithMockUser(username = "admin", password = "adminPass", roles = "ADMIN")
    @Test
    public void givenPostUriWithByAdmin_whenInsertAuthor()
            throws Exception {
            mockMvc.perform(post("/author")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(responseBody())
                            .accept(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk());
    }

    @WithMockUser(username = "user", password = "user1Pass")
    @Test
    public void givenViewAuthorUri_whenGetAuthor()
        throws Exception {
        mockMvc.perform(get("/author")
                        .accept(MediaType.TEXT_HTML))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("authors"));

    }

    private String responseBody() throws Exception {
        Map<String, String> body = new HashMap<>();

        body.put("name", "phu");
        body.put("strDob", "12/2/2000");

        ObjectMapper objectMapper = new ObjectMapper();

        return  objectMapper.writeValueAsString(body);
    }

}
