package com.example.client.service;

import ru.psbank.message_gate.out_message_service.ConsumeOutMessageRequest;
import ru.psbank.message_gate.out_message_service.ConsumeOutMessageResponse;

public interface SoapClient {

    ConsumeOutMessageResponse sendSms(ConsumeOutMessageRequest request);

}
