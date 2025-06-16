package com.igladkikh.subscriptionservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@RefreshScope
@EnableFeignClients
public class SubscriptionServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(SubscriptionServiceApplication.class, args);
    }

}
