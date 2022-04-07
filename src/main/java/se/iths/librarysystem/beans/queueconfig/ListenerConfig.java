package se.iths.librarysystem.beans.queueconfig;


import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.context.annotation.Configuration;
import se.iths.librarysystem.entity.TaskEntity;
import se.iths.librarysystem.queue.ReceiverHandler;

@Configuration
public class ListenerConfig {

    private static final String QUEUE_NAME = "book-loan-queue";
    private final ReceiverHandler receiverHandler;

    public ListenerConfig(ReceiverHandler receiverHandler) {
        this.receiverHandler = receiverHandler;
    }

    @RabbitListener(queues = QUEUE_NAME)
    public void listen(TaskEntity loanTask) {
        receiverHandler.loanBook(loanTask);
    }

}
