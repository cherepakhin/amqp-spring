package ru.perm.v.amqp.client.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.*;
import org.springframework.amqp.remoting.client.AmqpProxyFactoryBean;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.perm.v.amqp.service.PersonService;

@Configuration
@Slf4j
public class PersonServiceConfig {

    @Bean
    Queue personQueue() {
        return new Queue(PersonService.class.getSimpleName());
    }

    @Bean
    AmqpProxyFactoryBean personServiceAmqp(
            AmqpTemplate amqpTemplate) {
        AmqpProxyFactoryBean factoryBean = new AmqpProxyFactoryBean();
        factoryBean.setAmqpTemplate(amqpTemplate);
        factoryBean.setServiceInterface(PersonService.class);
        factoryBean.setRoutingKey(PersonService.class.getSimpleName());
        return factoryBean;
    }

    @Bean
    Binding personBinding(@Qualifier("personQueue") Queue personQueue, DirectExchange exchange) {
        return BindingBuilder
                .bind(personQueue)
                .to(exchange)
                .with(PersonService.class.getSimpleName());
    }
}
