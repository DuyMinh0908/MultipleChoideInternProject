package com.fpt.onlineTest.swagger2Config;

import com.github.javafaker.Faker;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class WebConfig {
    @Bean
    Faker faker(){
        return  new Faker();
    }
}
