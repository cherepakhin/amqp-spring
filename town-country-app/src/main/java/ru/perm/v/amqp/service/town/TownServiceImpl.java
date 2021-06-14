package ru.perm.v.amqp.service.town;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.perm.v.amqp.dto.Town;
import ru.perm.v.amqp.service.TownService;

import java.util.Arrays;
import java.util.List;

@Slf4j
@Service
public class TownServiceImpl implements TownService {
    List<Town> towns = Arrays.asList(
            new Town(1, "Perm"),
            new Town(2, "Moscow")
    );

    @Override
    public Town getById(Integer id) {
        log.info("id:{}", id);
        return towns.stream().filter(t -> t.getId().equals(id)).findFirst().orElse(new Town(-1, "NOT FOUND"));
    }

    @Override
    public List<Town> getAll() {
        log.info("all");
        return towns;
    }
}
