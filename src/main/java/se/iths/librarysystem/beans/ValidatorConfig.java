package se.iths.librarysystem.beans;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import se.iths.librarysystem.service.PersonService;
import se.iths.librarysystem.validatorservice.PersonValidator;

@Configuration
public class ValidatorConfig {

    @Bean
    @Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
    public static PersonValidator createPersonValidator(PersonService personService) {
        return new PersonValidator(personService);
    }

}
