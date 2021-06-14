package ru.perm.v.amqp.client.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import ru.perm.v.amqp.dto.Person;
import ru.perm.v.amqp.service.PersonService;

import static java.util.Arrays.asList;
import static org.hamcrest.Matchers.containsString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(PersonController.class)
class PersonControllerTest {

    @MockBean
    PersonService personService;
    @Autowired
    private MockMvc mockMvc;

    @Test
    void echo() throws Exception {
        this.mockMvc.perform(get("/person/echo/AAA"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("AAA")));
    }

    @Test
    void getById() throws Exception {
        when(personService.getById(1)).thenReturn(new Person(1, "AAA", 10));
        this.mockMvc.perform(get("/person/1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("{\"id\":1,\"name\":\"AAA\",\"age\":10}")));
    }

    @Test
    void getAll() throws Exception {
        when(personService.getAll()).thenReturn(asList(new Person(1, "AAA", 10)));
        this.mockMvc.perform(get("/person/all"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("[{\"id\":1,\"name\":\"AAA\",\"age\":10}]")));
    }
}