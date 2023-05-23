package com.example.ecommercebackend;

import com.stripe.Stripe;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication(exclude = org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration.class)
public class EcommerceBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(EcommerceBackendApplication.class, args);
        Stripe.apiKey = "sk_test_51NAPhSDNDkGZR7LmgrgVxe72tZOA9NLrnoDSIIuXO1yeWF0rAPdgvppwRJC8IMka19ZcL1k56Tnjqmq9cYghbyhw00EShaFrD1";

    }

}
