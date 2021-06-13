package ru.perm.v.amqp.service;

import ru.perm.v.amqp.entity.Person;

import java.util.List;

public interface PersonService {
    Person getById(Integer id);

    List<Person> getAll();
}
