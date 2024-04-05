package com.example.client.config.properties;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;
import org.springframework.validation.annotation.Validated;

@Getter
@Validated
@ConstructorBinding
@RequiredArgsConstructor
@ConfigurationProperties("sms-service-client")
public class RootProperties {

    private final SoapClientProperties soapClient;
    private final ServerProperties server;
    private final MarshallerProperties marshaller;

}
