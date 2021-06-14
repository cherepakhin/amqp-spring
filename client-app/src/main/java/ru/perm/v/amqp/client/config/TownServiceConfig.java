package ru.perm.v.amqp.client.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.*;
import org.springframework.amqp.remoting.client.AmqpProxyFactoryBean;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.perm.v.amqp.service.TownService;

@Configuration
@Slf4j
public class TownServiceConfig {
    // При объявлении через бин, очередь будет создаваться автоматом
    @Bean
    Queue townQueue() {
        return new Queue(TownService.class.getSimpleName());
    }

    @Bean
    AmqpProxyFactoryBean townServiceAmqp(AmqpTemplate amqpTemplate) {
        AmqpProxyFactoryBean factoryBean = new AmqpProxyFactoryBean();
        factoryBean.setAmqpTemplate(amqpTemplate);
        factoryBean.setServiceInterface(TownService.class);
        factoryBean.setRoutingKey(TownService.class.getSimpleName());
        return factoryBean;
    }

    @Bean
    Binding townBinding(@Qualifier("townQueue") Queue townQueue, DirectExchange exchange) {
        return BindingBuilder
                .bind(townQueue)
                .to(exchange)
                .with(TownService.class.getSimpleName());
    }
}
