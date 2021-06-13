package ru.perm.v.amqp.service.town;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.remoting.service.AmqpInvokerServiceExporter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.perm.v.amqp.service.CountryService;

@Configuration
public class CountryConfig {

    @Bean
    AmqpInvokerServiceExporter countryExporter(CountryService countryService, AmqpTemplate template) {
        AmqpInvokerServiceExporter exporter = new AmqpInvokerServiceExporter();
        exporter.setServiceInterface(CountryService.class);
        exporter.setService(countryService);
        exporter.setAmqpTemplate(template);
        return exporter;
    }

    // При объявлении через бин, очередь будет создаваться автоматом
    @Bean
    Queue countryQueue() {
        return new Queue(CountryService.class.getSimpleName());
    }

    @Bean
    SimpleMessageListenerContainer coutryListener(
            ConnectionFactory factory,
            @Qualifier("countryExporter") AmqpInvokerServiceExporter countryExporter,
            @Qualifier("countryQueue") Queue countryQueue) {
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer(factory);
        container.setMessageListener(countryExporter);
        container.setQueueNames(countryQueue.getName());
        return container;
    }
}
