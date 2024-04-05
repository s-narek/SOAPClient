package com.example.client.service.impl;

import com.example.client.config.properties.RootProperties;
import com.example.client.service.SoapClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.ws.client.core.WebServiceTemplate;
import ru.psbank.message_gate.out_message_service.ConsumeOutMessageRequest;
import ru.psbank.message_gate.out_message_service.ConsumeOutMessageResponse;

@Service
@RequiredArgsConstructor
public class SoapClientImpl implements SoapClient {
    private final WebServiceTemplate webServiceTemplate;
    private final RootProperties rootProperties;

    @Override
    public ConsumeOutMessageResponse sendSms(ConsumeOutMessageRequest request) {
        var serverProperties = rootProperties.getServer();
        return (ConsumeOutMessageResponse) webServiceTemplate
                .marshalSendAndReceive(serverProperties.getUrl(), request);
    }
}
