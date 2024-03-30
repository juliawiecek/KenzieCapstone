package com.kenzie.appserver.config;

import com.kenzie.capstone.service.client.LambdaServiceClient;
import com.kenzie.capstone.service.client.TVShowServiceClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LambdaServiceClientConfiguration {

    @Bean
    public TVShowServiceClient referralServiceClient() {
        return new TVShowServiceClient();
    }
}
