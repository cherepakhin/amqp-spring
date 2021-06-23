package ru.perm.v.amqp.client.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.*;
import org.springframework.amqp.remoting.client.AmqpProxyFactoryBean;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.perm.v.amqp.service.CountryService;

@Configuration
@Slf4j
public class CountryServiceConfig {
    @Bean
    Queue countryQueue() {
        return new Queue(CountryService.class.getSimpleName()+"Queue");
    }

    @Bean
    AmqpProxyFactoryBean countryServiceAmqp(AmqpTemplate amqpTemplate) {
        AmqpProxyFactoryBean factoryBean = new AmqpProxyFactoryBean();
        factoryBean.setAmqpTemplate(amqpTemplate);
        factoryBean.setServiceInterface(CountryService.class);
        factoryBean.setRoutingKey(CountryService.class.getSimpleName());
        return factoryBean;
    }

    @Bean
    Binding countryBinding(@Qualifier("countryQueue") Queue countryQueue, DirectExchange exchange) {
        return BindingBuilder
                .bind(countryQueue)
                .to(exchange)
                .with(CountryService.class.getSimpleName());
    }
}
