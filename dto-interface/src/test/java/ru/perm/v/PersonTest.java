package ru.perm.v;

import org.junit.jupiter.api.Test;
import ru.perm.v.amqp.dto.Person;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PersonTest {
    @Test
    void createPerson() {
        Person person = new Person(0, "-", 1);
        assertEquals("-", person.getName());
    }
}