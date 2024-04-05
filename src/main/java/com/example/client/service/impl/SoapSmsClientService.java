package com.example.client.service.impl;

import com.example.client.config.properties.RootProperties;
import com.example.client.exception.ServiceException;
import com.example.client.service.SmsClientService;
import com.example.client.service.SoapClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import ru.psbank.message_gate.out_message_service.Auth;
import ru.psbank.message_gate.out_message_service.ConsumeOutMessageArg;
import ru.psbank.message_gate.out_message_service.ConsumeOutMessageRequest;
import ru.psbank.message_gate.out_message_service.ConsumeOutMessageResponse;
import ru.psbank.message_gate.out_message_service.ConsumeOutMessageResponseCode;
import ru.psbank.message_gate.out_message_service.OutMessageTemplate;

@Component
@RequiredArgsConstructor
@Slf4j
public class SoapSmsClientService implements SmsClientService {

    private final SoapClient soapClient;
    private final RootProperties rootProperties;

    @Override
    public void sendSms(String phone, String otp) {
        var soapClientProperties = rootProperties.getSoapClient();

        var authLogin = soapClientProperties.getLogin();
        var authPassword = soapClientProperties.getPassword();
        var auth = createAuth(authLogin, authPassword);

        var messageTemplate = soapClientProperties.getMessageTemplate();
        var consumeOutMessageArg = createConsumeOutMessageArg(messageTemplate, phone, otp);

        var sms = createConsumeOutMessageRequest(auth, consumeOutMessageArg);
        var response = soapClient.sendSms(sms);

        processResponse(response);
    }

    private void processResponse(ConsumeOutMessageResponse response) {
        if (response.getResponseCode() == ConsumeOutMessageResponseCode.OK) {
            log.info("Sms sent");
        } else if (response.getResponseCode() == ConsumeOutMessageResponseCode.ERROR_SYSTEM_BLOCKED) {
            var errorMessage = "Sms not sent, error: system blocked";
            log.error(errorMessage);
            throw new ServiceException(errorMessage);
        } else if (response.getResponseCode() == ConsumeOutMessageResponseCode.ERROR_MESSAGE_ID_DUPLICATE) {
            var errorMessage = "Sms not sent, error: messageId duplicate";
            log.error(errorMessage);
            throw new ServiceException(errorMessage);
        }
    }

    private Auth createAuth(String login, String password) {
        var auth = new Auth();
        auth.setLogin(login);
        auth.setPassword(password);
        return auth;
    }

    private OutMessageTemplate createOutMessageTemplate(String message) {
        var outMessageTemplate = new OutMessageTemplate();
        outMessageTemplate.setText(message);
        return outMessageTemplate;
    }

    private ConsumeOutMessageArg createConsumeOutMessageArg(String messageTemplate, String phone, String otp) {
        var outMessageTemplate = createOutMessageTemplate(String.format(messageTemplate, otp));

        var consumeOutMessageArg = new ConsumeOutMessageArg();
        consumeOutMessageArg.setAddress(phone);
        consumeOutMessageArg.setOutMessageTemplate(outMessageTemplate);
        return consumeOutMessageArg;
    }

    private ConsumeOutMessageRequest createConsumeOutMessageRequest(Auth auth, ConsumeOutMessageArg consumeOutMessageArg) {
        var consumeOutMessageRequest = new ConsumeOutMessageRequest();
        consumeOutMessageRequest.setAuth(auth);
        consumeOutMessageRequest
                .getConsumeOutMessageArg()
                .add(consumeOutMessageArg);
        return consumeOutMessageRequest;
    }
}
