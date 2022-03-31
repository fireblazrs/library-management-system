package se.iths.librarysystem.beans.queueconfig;


import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.context.annotation.Configuration;
import se.iths.librarysystem.entity.LoanTaskEntity;

@Configuration
public class ListenerConfig {

    private static final String QUEUE_NAME = "book-loan-queue";

    @RabbitListener(queues = QUEUE_NAME)
    public void listen(LoanTaskEntity loanTask) {
        System.out.println("Message from queue: LoanTask " + loanTask.getId() + " received!");

        //do something with loanTask
    }

}
