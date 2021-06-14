package ru.perm.v.amqp.client.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.perm.v.amqp.dto.Country;
import ru.perm.v.amqp.service.CountryService;

import java.util.List;

@RestController
@RequestMapping("/country")
public class CountryController {

    CountryService countryService;

    public CountryController(CountryService countryService) {
        this.countryService = countryService;
    }

    @GetMapping("/echo/{msg}")
    public String echo(@PathVariable String msg) {
        return msg;
    }

    @GetMapping("/{id}")
    public Country getById(@PathVariable Integer id) {
        return countryService.getById(id);
    }

    @GetMapping("/all")
    public List<Country> getById() {
        return countryService.getAll();
    }
}
