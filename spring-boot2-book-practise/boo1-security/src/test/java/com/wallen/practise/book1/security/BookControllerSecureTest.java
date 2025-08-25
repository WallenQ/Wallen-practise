package com.wallen.practise.book1.security;

import com.wallen.practise.book1.security.controller.BookController;
import com.wallen.practise.book1.security.entity.Book;
import com.wallen.practise.book1.security.service.BookService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @Author qianwenlong
 * @Date 2025/8/25 9:09
 */
@RunWith(SpringRunner.class)
@WebMvcTest(BookController.class)
@WithMockUser
public class BookControllerSecureTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private BookService bookService;

    @Test
    public void shouldAddBook() throws Exception {
        when(bookService.create(any(Book.class))).thenReturn(new Book("123456789", "Test Book Stored", "T.Author"));

        mockMvc.perform(post("/books").contentType(MediaType.APPLICATION_JSON)
                .content("{\"isbn\":\"123456789\"},\"title\":\"Test Book\",\"authors\":[\"T.Author\"]")
                .with(csrf())).andExpect(status().isCreated()).andExpect(header().string("Location", "http://localhost/books/123456789"));
    }
}
