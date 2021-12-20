package com.example.myShop.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.security.Principal;

/**
 * @author nafis
 * @since 20.12.2021
 */

@Configuration
public class MyShopApplicationConfiguration {
    @Bean
    public Principal principal(){
        return new Principal() {
            @Override
            public String getName() {
                return null;
            }
        };
    }
}
