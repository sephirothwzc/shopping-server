package com.wzc.shoppingserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class ShoppingServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(ShoppingServerApplication.class, args);
    }

}
