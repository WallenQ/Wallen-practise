package com.wallen.practise.book1.security;

import com.wallen.practise.book1.security.entity.Book;
import com.wallen.practise.book1.security.service.BookService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.util.Arrays;

import static org.mockito.Mockito.when;

/**
 * @Author qianwenlong
 * @Date 2025/8/28 16:21
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource(properties = "spring.security.user.password=s3cr3t")
public class BookControllerIntegrationWebClientTest {
    @Autowired
    private WebTestClient webTestClient;
    @MockBean
    private BookService bookService;

    @Test
    public void shouldReturnListOfBooks() throws Exception {
        when(bookService.findAll())
                .thenReturn(Arrays.asList(new Book("123", "Spring 5 Recipes", "Marten Deinum", "Josh Long"),
                        new Book("321", "Pro Spring MVC", "Marten Deinum", "Colin Yates")));

        webTestClient.get().uri("/books").headers(headers -> headers.setBasicAuth("user", "s3cr3t"))
                .exchange().expectStatus().isOk().expectBodyList(Book.class).hasSize(2);
    }
}
