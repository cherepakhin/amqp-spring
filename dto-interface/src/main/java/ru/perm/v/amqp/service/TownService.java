package ru.perm.v.amqp.service;

import ru.perm.v.amqp.dto.Town;

import java.util.List;

public interface TownService {
    Town getById(Integer id);

    List<Town> getAll();
}
