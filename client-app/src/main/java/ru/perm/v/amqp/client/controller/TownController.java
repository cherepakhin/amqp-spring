package ru.perm.v.amqp.client.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.perm.v.amqp.dto.Town;
import ru.perm.v.amqp.service.TownService;

import java.util.List;

@RestController
@RequestMapping("/town")
public class TownController {

    TownService townService;

    public TownController() {
    }

    public TownController(TownService townService) {
        this.townService = townService;
    }

    @GetMapping("/echo/{msg}")
    public String echo(@PathVariable String msg) {
        return msg;
    }

    @GetMapping("/{id}")
    public Town getById(@PathVariable Integer id) {
        return townService.getById(id);
    }

    @GetMapping("/all")
    public List<Town> getAll() {
        return townService.getAll();
    }
}
