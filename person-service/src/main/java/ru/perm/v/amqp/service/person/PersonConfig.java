package ru.perm.v.amqp.service.person;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.remoting.service.AmqpInvokerServiceExporter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.perm.v.amqp.service.PersonService;

@Configuration
public class PersonConfig {
    @Bean
    Queue queue() {
        return new Queue(PersonService.class.getSimpleName());
    }

    @Bean
    AmqpInvokerServiceExporter exporter(PersonService personService, AmqpTemplate template) {
        AmqpInvokerServiceExporter exporter = new AmqpInvokerServiceExporter();
        exporter.setServiceInterface(PersonService.class);
        exporter.setService(personService);
        exporter.setAmqpTemplate(template);
        return exporter;
    }

    @Bean
    SimpleMessageListenerContainer listener(ConnectionFactory factory, AmqpInvokerServiceExporter exporter, Queue queue) {
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer(factory);
        container.setMessageListener(exporter);
        container.setQueueNames(queue.getName());
        return container;
    }

}
