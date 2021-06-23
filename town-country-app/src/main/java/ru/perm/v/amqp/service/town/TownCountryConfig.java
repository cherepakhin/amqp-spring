package ru.perm.v.amqp.service.town;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.perm.v.amqp.service.CountryService;
import ru.perm.v.amqp.service.TownService;

import java.util.Arrays;

@Configuration
public class TownCountryConfig {

    @Bean
    RabbitRpcExporter rpcComponent(ConfigurableApplicationContext context) {
        return new RabbitRpcExporter(Arrays.asList(TownService.class, CountryService.class), context);
    }

//    @Bean
//    RpcComponent countryComponent(ConfigurableApplicationContext context,
//                               DefaultListableBeanFactory factory) {
//        return new RpcComponent(CountryService.class, context, factory);
//    }
}
