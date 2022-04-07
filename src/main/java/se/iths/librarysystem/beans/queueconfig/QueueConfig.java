package se.iths.librarysystem.beans.queueconfig;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class QueueConfig {

    private static final String QUEUE_NAME = "book-loan-queue";

    @Bean
    public Queue myQueue() {
        return new Queue(QUEUE_NAME, false);
    }

}
