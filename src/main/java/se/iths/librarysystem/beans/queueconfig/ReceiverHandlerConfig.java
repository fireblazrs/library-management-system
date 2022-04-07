package se.iths.librarysystem.beans.queueconfig;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import se.iths.librarysystem.queue.ReceiverHandler;
import se.iths.librarysystem.service.BookService;
import se.iths.librarysystem.service.TaskService;
import se.iths.librarysystem.service.UserService;

@Configuration
public class ReceiverHandlerConfig {

    @Bean
    @Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
    public static ReceiverHandler receiverHandler(BookService bookService, UserService userService,
                                                  TaskService taskService) {
        return new ReceiverHandler(bookService, userService, taskService);
    }
}
