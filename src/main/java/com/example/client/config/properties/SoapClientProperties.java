package com.example.client.config.properties;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class SoapClientProperties {

    private final String login;
    private final String password;
    private final String messageTemplate;

}
