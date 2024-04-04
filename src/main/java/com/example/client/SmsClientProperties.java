package com.example.client;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Data
public class SmsClientProperties {
    @Value("${sms-service-client.login}")
    public final String login;
    @Value("${sms-service-client.password}")
    public final String password;
    @Value("${sms-service-client.message}")
    public final String message;
}
