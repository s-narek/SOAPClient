package com.example.client;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties("sms-service-client.reporting")
@Data
public class SmsClientProperties {
    private String login;
    private String password;
    private String message;
}
