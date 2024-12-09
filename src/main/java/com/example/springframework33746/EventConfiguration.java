package com.example.springframework33746;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class EventConfiguration {

    @Bean
    EventGenerator eventGenerator() {
        return new EventGenerator();
    }
}
