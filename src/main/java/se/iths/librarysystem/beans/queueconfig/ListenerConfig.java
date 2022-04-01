package se.iths.librarysystem.beans.queueconfig;


import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.context.annotation.Configuration;
import se.iths.librarysystem.entity.LoanTaskEntity;
import se.iths.librarysystem.queue.ReceiverHandler;

@Configuration
public class ListenerConfig {

    private static final String QUEUE_NAME = "book-loan-queue";
    private final ReceiverHandler receiverHandler;

    public ListenerConfig(ReceiverHandler receiverHandler) {
        this.receiverHandler = receiverHandler;
    }

    @RabbitListener(queues = QUEUE_NAME)
    public void listen(LoanTaskEntity loanTask) {
        receiverHandler.loanBook(loanTask);
        System.out.println("Message from queue: LoanTask " + loanTask.getId() + " handled!");
    }

}
