package ru.perm.v.amqp.client;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class ClientApp {
    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(ClientApp.class, args);
    }
}
