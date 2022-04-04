package se.iths.librarysystem.security;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
public class SecurityConfig {

    // Man vill oftast ha CSRF skydd på "/home, /admin, /login
    // men behöver inte CSRF skyddet på apier

    // first: create UserDetailsService & UserPrincipal - need to add password field & encryption
    private final String[] urls = {"/api/*", "/logout"};

    @Bean
    public SecurityFilterChain configure(HttpSecurity http) throws Exception {
        return http
                .csrf().ignoringAntMatchers(urls)
                .and()
                .httpBasic()
                .and()
                .authorizeRequests()
                //.antMatchers("/roles").hasRole("ADMIN")
                .antMatchers("/api/users/").permitAll() //be more specific about create user endpoint
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/login").permitAll()
                .and()
                .logout().permitAll()
                .and().build();
    }

}
