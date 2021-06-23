package ru.perm.v.amqp.service.town;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import ru.perm.v.amqp.service.CountryService;
import ru.perm.v.amqp.service.TownService;

@SpringBootApplication
public class TownApp {
    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(TownApp.class, args);
        System.out.println(context.getBean(TownService.class));
        System.out.println(context.getBean(TownService.class.getSimpleName()+"Queue"));
//        System.out.println(context.getBean(TownService.class.getSimpleName()+"Exporter"));
//        System.out.println(context.getBean(TownService.class.getSimpleName()+"Listener"));
//        System.out.println(context.getBean(CountryService.class));
//        System.out.println(context.getBean(CountryService.class.getSimpleName()+"Listener"));
    }
}
