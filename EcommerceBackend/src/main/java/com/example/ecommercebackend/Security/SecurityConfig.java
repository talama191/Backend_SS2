package com.example.ecommercebackend.Security;

import com.example.ecommercebackend.User.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {


    @Autowired
    UserService userService;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http.csrf().disable()
                .authorizeHttpRequests()
                .requestMatchers("/Product").permitAll() // với endpoint /hello thì sẽ được cho qua
                .and().authorizeHttpRequests()
                .requestMatchers("/Products").permitAll() // với endpoint /hello thì sẽ được cho qua
                .and()
                .authorizeHttpRequests()
                .requestMatchers("/Users").authenticated()
                .and().formLogin()
                .and()
                .authorizeHttpRequests()
                .requestMatchers("/User/**").authenticated()
                .and().formLogin()
                .and().build();
    }
}
