package se.iths.librarysystem.beans.rabbitmq;


import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    public static final String LOG_QUEUE = "logQueue";

    @Bean
    public Queue myQueue() {
        return new Queue("myQueue", false);
    }

    @Bean
    public ApplicationRunner runner(RabbitTemplate template) {
        return args -> template.convertAndSend("myQueue", "Hello, world");
    }

    @RabbitListener(queues = "myQueue")
    public void listen(String input) {
        System.out.println("Message from myQueue: " + input);
    }

}
