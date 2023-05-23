package com.example.ecommercebackend.Security;

import com.example.ecommercebackend.Entities.User.UserService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Autowired
    UserService userService;

    @Bean
    DaoAuthenticationProvider authProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userService);
        authProvider.setPasswordEncoder(getPassWordEncoder());
        return authProvider;
    }

    @Bean
    public ProviderManager authManagerBean(AuthenticationProvider provider) throws Exception {
        return new ProviderManager(provider);
    }

    @Bean
    public AuthenticationManager authenticationManager() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userService);
        authProvider.setPasswordEncoder(getPassWordEncoder());
        return new ProviderManager(authProvider);
    }

    @Bean
    public PasswordEncoder getPassWordEncoder() {
        return new BCryptPasswordEncoder(15);
    }

    @Autowired
    JwtTokenFilter jwtTokenFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http.csrf().disable().cors().and()
                .authorizeHttpRequests()
                .requestMatchers("/product").hasRole("ADMIN")
                .and()
                .authorizeHttpRequests()
                .requestMatchers("/products").permitAll()
                .and()
                .authorizeHttpRequests()
                .requestMatchers("/products/**").permitAll()
                .and()
                .authorizeHttpRequests()
                .requestMatchers("/categories/**").permitAll()
                .and()
                .authorizeHttpRequests()
                .requestMatchers("/categories").permitAll()
                .and()
                .authorizeHttpRequests()
                .requestMatchers("/users").authenticated()
                .and()
                .authorizeHttpRequests()
                .requestMatchers("/user/**").authenticated()
                .and()
                .authorizeHttpRequests()
                .requestMatchers("/user-auth/authenticate").permitAll()
                .and()
                .authorizeHttpRequests()
                .requestMatchers("/user-register/**").permitAll()
                .and()
                .authorizeHttpRequests()
                .requestMatchers("/cart").permitAll()
                .and()
                .authorizeHttpRequests()
                .requestMatchers("/cart/**").permitAll()
                .and()
                .authorizeHttpRequests()
                .requestMatchers("/brands/**").permitAll()
                .and()
                .authorizeHttpRequests()
                .requestMatchers("/cart_lines/**").permitAll()
                .and()
                .authorizeHttpRequests()
                .requestMatchers("/checkout").permitAll()
                .and()
                .authorizeHttpRequests()
                .requestMatchers("/checkout_success").permitAll();
        http.exceptionHandling()
                .authenticationEntryPoint(
                        (request, response, ex) -> {
                            response.sendError(
                                    HttpServletResponse.SC_UNAUTHORIZED,
                                    ex.getMessage()
                            );
                        }
                );

        http.addFilterBefore(jwtTokenFilter, UsernamePasswordAuthenticationFilter.class);
        http.authenticationProvider(authProvider());
        return http.build();
    }
    @Bean
    CorsConfigurationSource corsConfigurationSource(){
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList("*"));
        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "PATCH", "DELETE", "OPTIONS"));
        configuration.setAllowedHeaders(Arrays.asList("authorization", "content-type", "x-auth-token"));
        configuration.setExposedHeaders(Arrays.asList("x-auth-token"));
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
}
