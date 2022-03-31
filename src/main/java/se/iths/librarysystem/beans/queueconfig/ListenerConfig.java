package se.iths.librarysystem.beans.queueconfig;


import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ListenerConfig {

    private static final String QUEUE_NAME = "book-loan-queue";

    @RabbitListener(queues = QUEUE_NAME)
    public void listen(String input) {
        System.out.println("Message from queue: " + input);
    }

}
