package se.iths.librarysystem.beans;

import org.modelmapper.ModelMapper;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import se.iths.librarysystem.queue.QueueHandler;

@Configuration
public class QueueHandlerConfig {

    @Bean
    @Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
    public QueueHandler queueHandler(RabbitTemplate template, ModelMapper modelMapper) {
        return new QueueHandler(template, modelMapper);
    }
}
