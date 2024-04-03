package com.example.client;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.xmlsoap.schemas.soap.envelope.Auth;
import org.xmlsoap.schemas.soap.envelope.GetOutMessageRequest;

@SpringBootApplication
public class ClientApplication {

    public static void main(String[] args) {
        SpringApplication.run(ClientApplication.class, args);
    }

}
