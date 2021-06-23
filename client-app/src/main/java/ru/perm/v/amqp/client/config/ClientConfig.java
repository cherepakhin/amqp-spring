package ru.perm.v.amqp.client.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.perm.v.amqp.service.CountryService;
import ru.perm.v.amqp.service.PersonService;
import ru.perm.v.amqp.service.TownService;

import static java.util.Arrays.asList;

@Configuration
@Slf4j
public class ClientConfig {
    String EXCHANGE = "dealer.rpc";

    // Нужен для астосоздания exchange
    @Bean
    public AmqpAdmin amqpAdmin(ConnectionFactory factory) {
        return new RabbitAdmin(factory);
    }

    @Bean
    DirectExchange exchange() {
        return new DirectExchange(EXCHANGE, true, false);
    }

    @Bean
    RabbitTemplate amqpTemplate(ConnectionFactory factory) {
        RabbitTemplate template = new RabbitTemplate(factory);
        template.setExchange(EXCHANGE);
        return template;
    }
}
