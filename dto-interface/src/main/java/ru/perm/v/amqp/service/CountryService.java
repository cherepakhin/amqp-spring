package ru.perm.v.amqp.service;

import ru.perm.v.amqp.dto.Country;

import java.util.List;

public interface CountryService {
    Country getById(Integer id);

    List<Country> getAll();
}
