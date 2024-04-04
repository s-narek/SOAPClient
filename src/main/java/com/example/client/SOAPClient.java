package com.example.client;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.ws.client.core.WebServiceTemplate;
import ru.psbank.message_gate.out_message_service.ConsumeOutMessageRequest;
import ru.psbank.message_gate.out_message_service.ConsumeOutMessageResponse;

@Service
@RequiredArgsConstructor
public class SOAPClient {
    private final WebServiceTemplate webServiceTemplate;

    @Value("${sms-service-client.server.url}")
    private String serverUrl;

    public ConsumeOutMessageResponse consumeOutMessage(ConsumeOutMessageRequest request) {
        return (ConsumeOutMessageResponse) webServiceTemplate.marshalSendAndReceive(serverUrl, request);
    }
}
