package se.iths.librarysystem.beans.queueconfig;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SenderConfig {

    private static final String QUEUE_NAME = "book-loan-queue";

    @Bean
    public ApplicationRunner runner(RabbitTemplate template) {
        return args -> template.convertAndSend(QUEUE_NAME, "Hello " + QUEUE_NAME);
    }

}
