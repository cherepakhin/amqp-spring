package ru.perm.v.amqp.service.person;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.perm.v.amqp.entity.Person;
import ru.perm.v.amqp.service.PersonService;

import java.util.Arrays;
import java.util.List;

@Slf4j
@Service
public class PersonServiceImpl implements PersonService {
    List<Person> persons = Arrays.asList(
            new Person(1, "Vasi", 54),
            new Person(2, "Anna", 25)
    );

    @Override
    public Person getById(Integer id) {
        return persons.stream().filter(t -> t.getId().equals(id)).findFirst().orElse(new Person(-1, "NOT FOUND", 0));
    }

    @Override
    public List<Person> getAll() {
        return persons;
    }
}
