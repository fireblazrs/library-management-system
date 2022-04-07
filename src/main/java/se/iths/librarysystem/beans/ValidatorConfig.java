package se.iths.librarysystem.beans;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import se.iths.librarysystem.service.BookService;
import se.iths.librarysystem.service.RoleService;
import se.iths.librarysystem.service.UserService;
import se.iths.librarysystem.validatorservice.BookValidator;
import se.iths.librarysystem.validatorservice.RoleValidator;
import se.iths.librarysystem.validatorservice.UserValidator;

@Configuration
public class ValidatorConfig {

    @Bean
    @Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
    public static UserValidator createPersonValidator(UserService userService) {
        return new UserValidator(userService);
    }

    @Bean
    @Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
    public static BookValidator bookValidator(BookService bookService) {
        return new BookValidator(bookService);
    }

    @Bean
    @Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
    public static RoleValidator roleValidator(RoleService roleService) {
        return new RoleValidator(roleService);
    }

}
