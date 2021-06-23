package ru.perm.v.amqp.service.town;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.remoting.service.AmqpInvokerServiceExporter;
import org.springframework.amqp.support.converter.SimpleMessageConverter;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.ConfigurableApplicationContext;

import javax.annotation.PostConstruct;
import java.util.List;

import static org.springframework.beans.factory.config.AutowireCapableBeanFactory.AUTOWIRE_BY_TYPE;

public class RabbitRpcExporter {
    private final String QUEUE ="Queue";
    private final String EXPORTER ="Exporter";
    private final String LISTENER ="Listener";
    private final ConfigurableApplicationContext context;
    List<Class> services;

    public RabbitRpcExporter(List<Class> services,
                        ConfigurableApplicationContext context) {
        this.services = services;
        this.context = context;
    }

    @PostConstruct
    public void createBeans() {
        ConfigurableListableBeanFactory factory = context.getBeanFactory();
        for (Class service : services) {
            Queue queue = new Queue(service.getSimpleName() + QUEUE);
            factory.autowireBeanProperties(queue, AUTOWIRE_BY_TYPE, true);
            factory.registerSingleton(queue.getName(), queue);

            AmqpInvokerServiceExporter exporter = new AmqpInvokerServiceExporter();
            exporter.setServiceInterface(service);
            exporter.setService(context.getBean(service));
            exporter.setAmqpTemplate(context.getBean(AmqpTemplate.class));
            exporter.setMessageConverter(new SimpleMessageConverter());

            factory.autowireBeanProperties(exporter, AUTOWIRE_BY_TYPE, false);
            factory.registerSingleton(service.getSimpleName() + EXPORTER, exporter);

            SimpleMessageListenerContainer listenerContainer = new SimpleMessageListenerContainer(context.getBean(ConnectionFactory.class));
            listenerContainer.setMessageListener(exporter);
            listenerContainer.setQueueNames(queue.getName());
            factory.registerSingleton(service.getSimpleName() + LISTENER, listenerContainer);
        }
    }

}
