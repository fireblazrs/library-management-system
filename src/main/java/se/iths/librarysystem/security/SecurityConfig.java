package se.iths.librarysystem.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    // Man vill oftast ha CSRF skydd på "/home, /admin, /login
    // men behöver inte CSRF skyddet på apier

    private final String[] urls = {"/api/*", "/logout"};

    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain configure(HttpSecurity http) throws Exception {
        return http
                .csrf().ignoringAntMatchers(urls)
                .and()
                .httpBasic()
                .and()
                .authorizeRequests()
                .antMatchers("/api/roles/*").hasRole("ADMIN")
                .antMatchers("/api/users/new", "/api/users/*", "/", "/home").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/login").permitAll()
                .and()
                .logout().permitAll()
                .and()
                .build();

    }

}
