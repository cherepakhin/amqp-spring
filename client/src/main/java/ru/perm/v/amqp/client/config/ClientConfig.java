package ru.perm.v.amqp.client.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.remoting.client.AmqpProxyFactoryBean;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.perm.v.amqp.service.CountryService;
import ru.perm.v.amqp.service.PersonService;
import ru.perm.v.amqp.service.TownService;

@Configuration
@Slf4j
public class ClientConfig {
    String EXCHANGE = "dealer.rpc";

    @Bean
    Queue townQueue() {
        return new Queue(TownService.class.getSimpleName());
    }

    @Bean
    Queue personQueue() {
        return new Queue(PersonService.class.getSimpleName());
    }

    @Bean
    Queue countryQueue() {
        return new Queue(CountryService.class.getSimpleName());
    }

    @Bean
    public AmqpAdmin amqpAdmin(ConnectionFactory factory) {
        return new RabbitAdmin(factory);
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
    AmqpProxyFactoryBean personServiceAmqp(AmqpTemplate amqpTemplate) {
        AmqpProxyFactoryBean factoryBean = new AmqpProxyFactoryBean();
        factoryBean.setAmqpTemplate(amqpTemplate);
        factoryBean.setServiceInterface(PersonService.class);
        factoryBean.setRoutingKey(PersonService.class.getSimpleName());
        return factoryBean;
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
    DirectExchange exchange() {
        return new DirectExchange(EXCHANGE, true, false);
    }

    @Bean
    Binding townBinding(@Qualifier("townQueue") Queue queue, DirectExchange exchange) {
        return BindingBuilder
                .bind(queue)
                .to(exchange)
                .with(TownService.class.getSimpleName());
    }

    @Bean
    Binding personBinding(@Qualifier("personQueue") Queue queue, DirectExchange exchange) {
        return BindingBuilder
                .bind(queue)
                .to(exchange)
                .with(PersonService.class.getSimpleName());
    }

    @Bean
    Binding countryBinding(@Qualifier("countryQueue") Queue queue, DirectExchange exchange) {
        return BindingBuilder
                .bind(queue)
                .to(exchange)
                .with(CountryService.class.getSimpleName());
    }

    @Bean
    RabbitTemplate amqpTemplate(ConnectionFactory factory) {
        RabbitTemplate template = new RabbitTemplate(factory);
        template.setExchange(EXCHANGE);
//        template.setUserCorrelationId(true);
        return template;
    }
}
