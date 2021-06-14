package ru.perm.v.amqp.service;

import ru.perm.v.amqp.dto.Person;

import java.util.List;

public interface PersonService {
    Person getById(Integer id);

    List<Person> getAll();
}
