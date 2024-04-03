package com.example.client;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ws.client.core.WebServiceTemplate;
import org.xmlsoap.schemas.soap.envelope.ConsumeOperatorEventRequest;
import org.xmlsoap.schemas.soap.envelope.ConsumeOperatorEventResponse;
import org.xmlsoap.schemas.soap.envelope.ConsumeOutMessageRequest;
import org.xmlsoap.schemas.soap.envelope.ConsumeOutMessageResponse;
import org.xmlsoap.schemas.soap.envelope.GetOutMessageDlvStatusRequest;
import org.xmlsoap.schemas.soap.envelope.GetOutMessageDlvStatusResponse;
import org.xmlsoap.schemas.soap.envelope.GetOutMessageRequest;
import org.xmlsoap.schemas.soap.envelope.GetOutMessageResponse;
import org.xmlsoap.schemas.soap.envelope.ProvideServiceStatusRequest;
import org.xmlsoap.schemas.soap.envelope.ProvideServiceStatusResponse;

import static com.example.client.Constants.URL_SERVICE;

@Service
@RequiredArgsConstructor
public class ClientService {
    private final WebServiceTemplate webServiceTemplate;

    public ConsumeOutMessageResponse consumeOutMessage(ConsumeOutMessageRequest request) {
        return (ConsumeOutMessageResponse) webServiceTemplate.marshalSendAndReceive(URL_SERVICE, request);
    }

    public GetOutMessageResponse getOutMessage(GetOutMessageRequest request) {
        return (GetOutMessageResponse) webServiceTemplate.marshalSendAndReceive(URL_SERVICE, request);
    }

    public GetOutMessageDlvStatusResponse getOutMessageDlvStatus(GetOutMessageDlvStatusRequest request) {
        return (GetOutMessageDlvStatusResponse) webServiceTemplate.marshalSendAndReceive(URL_SERVICE, request);
    }

    public ConsumeOperatorEventResponse provideAddressOperator(ConsumeOperatorEventRequest request) {
        return (ConsumeOperatorEventResponse) webServiceTemplate.marshalSendAndReceive(URL_SERVICE, request);
    }

    public ProvideServiceStatusResponse provideServiceStatus(ProvideServiceStatusRequest request) {
        return (ProvideServiceStatusResponse) webServiceTemplate.marshalSendAndReceive(URL_SERVICE, request);
    }
}
