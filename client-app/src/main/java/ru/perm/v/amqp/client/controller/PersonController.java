package ru.perm.v.amqp.client.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.perm.v.amqp.dto.Person;
import ru.perm.v.amqp.service.PersonService;

import java.util.List;

@RestController
@RequestMapping("/person")
public class PersonController {

    PersonService personService;

    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping("/echo/{msg}")
    public String echo(@PathVariable String msg) {
        return msg;
    }

    @GetMapping("/{id}")
    public Person getById(@PathVariable Integer id) {
        return personService.getById(id);
    }

    @GetMapping("/all")
    public List<Person> getById() {
        return personService.getAll();
    }
}
