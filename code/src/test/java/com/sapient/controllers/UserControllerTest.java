package com.sapient.controllers;

import com.sapient.models.User;
import com.sapient.services.UserService;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Arrays;
import java.util.Optional;

@ExtendWith(SpringExtension.class)
@WebMvcTest(UserController.class)
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @Test
    public void getUser() throws Exception {

        User user = new User();
        user.setId(15);
        user.setName("Mocked Name");

        BDDMockito.given(this.userService.getUser(15)).willReturn(Optional.of(user));

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/api/v1/user/{userId}", 15)
                .contentType("application/json");
        MvcResult result = mockMvc.perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();
        Assert.assertEquals("Incorrect user",
                "{\"id\":15,\"name\":\"Mocked Name\"}",
                result.getResponse().getContentAsString());
    }

    @Test
    public void getUsers() throws Exception {

        User user1 = new User();
        user1.setId(14);
        user1.setName("Mocked Name 1");

        User user2 = new User();
        user2.setId(15);
        user2.setName("Mocked Name 2");

        User user3 = new User();
        user3.setId(16);
        user3.setName("Mocked Name 3");

        final String RESPONSE_JSON =
                "[{\"id\":14,\"name\":\"Mocked Name 1\"}," +
                        "{\"id\":15,\"name\":\"Mocked Name 2\"}," +
                        "{\"id\":16,\"name\":\"Mocked Name 3\"}]";

        BDDMockito.given(this.userService.getUsers()).willReturn(Arrays.asList(user1, user2, user3));

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/api/v1/users")
                .contentType("application/json");
        MvcResult result = mockMvc.perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();
        Assert.assertEquals("Incorrect user",
                RESPONSE_JSON,
                result.getResponse().getContentAsString());
    }
}
