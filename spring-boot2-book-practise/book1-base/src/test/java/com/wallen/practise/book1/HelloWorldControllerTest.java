package com.wallen.practise.book1;

import com.wallen.practise.book1.controller.HelloWorldController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.asyncDispatch;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.request;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author Wallen
 * @date 2024/9/17 14:18
 */
@RunWith(SpringRunner.class)
@WebMvcTest(HelloWorldController.class)
public class HelloWorldControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testHelloWorldController() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/helloWorld")).andExpect(status().isOk())
                .andExpect(content().string("hello world"))
                .andExpect(content().contentTypeCompatibleWith(MediaType.TEXT_PLAIN));
    }

    @Test
    public void testHello1() throws Exception {
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/hello1"))
                .andExpect(request().asyncStarted()).andDo(MockMvcResultHandlers.log()).andReturn();

        mockMvc.perform(asyncDispatch(mvcResult)).andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.TEXT_PLAIN))
                .andExpect(content().string("Hello World, from Spring Boot 2!"));
    }

    @Test
    public void testHello2() throws Exception {
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/hello2"))
                .andExpect(request().asyncStarted()).andDo(MockMvcResultHandlers.log()).andReturn();

        mockMvc.perform(asyncDispatch(mvcResult)).andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.TEXT_PLAIN))
                .andExpect(content().string("Hello World, from Spring Boot 2!"));
    }
}
