package ru.perm.v.amqp.service.town.config;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.remoting.service.AmqpInvokerServiceExporter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.perm.v.amqp.service.TownService;

//@Configuration
public class TownConfig {

    @Bean
    AmqpInvokerServiceExporter townExporter(TownService townService, AmqpTemplate template) {
        AmqpInvokerServiceExporter exporter = new AmqpInvokerServiceExporter();
        exporter.setServiceInterface(TownService.class);
        exporter.setService(townService);
        exporter.setAmqpTemplate(template);
        return exporter;
    }

    @Bean
    Queue townQueue() {
        return new Queue(TownService.class.getSimpleName());
    }

    @Bean
    SimpleMessageListenerContainer townListener(
            ConnectionFactory factory,
            @Qualifier("townExporter") AmqpInvokerServiceExporter townExporter,
            @Qualifier("townQueue") Queue townQueue) {
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer(factory);
        container.setMessageListener(townExporter);
        container.setQueueNames(townQueue.getName());
        return container;
    }
}
