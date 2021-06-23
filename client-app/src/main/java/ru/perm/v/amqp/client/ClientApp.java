package ru.perm.v.amqp.client;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import ru.perm.v.amqp.service.TownService;

@SpringBootApplication
public class ClientApp {
    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(ClientApp.class, args);
        System.out.println(context.getBean(TownService.class.getSimpleName()));
    }
}
