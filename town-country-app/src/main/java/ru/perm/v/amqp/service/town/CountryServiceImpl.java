package ru.perm.v.amqp.service.town;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.perm.v.amqp.dto.Country;
import ru.perm.v.amqp.service.CountryService;

import java.util.Arrays;
import java.util.List;

@Slf4j
@Service
public class CountryServiceImpl implements CountryService {
    List<Country> countries = Arrays.asList(
            new Country(1, "Russia"),
            new Country(2, "France")
    );

    @Override
    public Country getById(Integer id) {
        log.info("id:{}", id);
        return countries.stream().filter(t -> t.getId().equals(id)).findFirst().orElse(new Country(-1, "NOT FOUND"));
    }

    @Override
    public List<Country> getAll() {
        log.info("all");
        return countries;
    }
}
