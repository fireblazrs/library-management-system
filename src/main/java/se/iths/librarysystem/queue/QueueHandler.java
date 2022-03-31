package se.iths.librarysystem.queue;

import org.modelmapper.ModelMapper;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import se.iths.librarysystem.dto.Task;
import se.iths.librarysystem.entity.LoanTaskEntity;

public class QueueHandler {

    private static final String QUEUE_NAME = "book-loan-queue";
    private final RabbitTemplate template;
    private final ModelMapper modelMapper;

    public QueueHandler(RabbitTemplate template, ModelMapper modelMapper) {
        this.template = template;
        this.modelMapper = modelMapper;
    }

    public Task sendToQueue(LoanTaskEntity loanTask) {
        template.convertAndSend(QUEUE_NAME, loanTask);
        Task task = modelMapper.map(loanTask, Task.class);
        task.setUrl("/tasks/" + task.getId());
        return task;
    }
}
