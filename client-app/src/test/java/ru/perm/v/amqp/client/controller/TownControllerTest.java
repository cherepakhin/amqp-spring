package ru.perm.v.amqp.client.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import ru.perm.v.amqp.dto.Town;
import ru.perm.v.amqp.service.TownService;

import static java.util.Arrays.asList;
import static org.hamcrest.Matchers.containsString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(TownController.class)
class TownControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    TownService townService;

    @Test
    void echo() throws Exception {
        this.mockMvc.perform(get("/town/echo/AAA"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("AAA")));
    }

    @Test
    void getById() throws Exception {
        when(townService.getById(1)).thenReturn(new Town(1, "AAA"));
        this.mockMvc.perform(get("/town/1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("AAA")));
    }

    @Test
    void getAll() throws Exception {
        when(townService.getAll()).thenReturn(asList(new Town(1, "AAA")));
        this.mockMvc.perform(get("/town/all"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("[{\"id\":1,\"name\":\"AAA\"}]")));
    }
}