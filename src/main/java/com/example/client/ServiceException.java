package com.example.client;

public class ServiceException extends RuntimeException {
    public ServiceException(String messag) {
        super(messag);
    }
}
