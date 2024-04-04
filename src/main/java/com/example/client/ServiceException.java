package com.example.client;

public class ServiceException extends RuntimeException {
    public ServiceException(String message) {
        super(message);
    }
}
