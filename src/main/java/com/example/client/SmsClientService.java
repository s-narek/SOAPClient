package com.example.client;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import ru.psbank.message_gate.out_message_service.Auth;
import ru.psbank.message_gate.out_message_service.ConsumeOutMessageArg;
import ru.psbank.message_gate.out_message_service.ConsumeOutMessageRequest;
import ru.psbank.message_gate.out_message_service.ConsumeOutMessageResponseCode;
import ru.psbank.message_gate.out_message_service.OutMessageTemplate;

@Component
@RequiredArgsConstructor
@Slf4j
public class SmsClientService {

    private final SOAPClient soapClient;
    private final SmsClientProperties properties;

    public void sendSms(String otp, String phone) {
        var auth = new Auth();
        auth.setLogin(properties.login);
        auth.setPassword(properties.password);

        var outMessageTemplate = new OutMessageTemplate();
        outMessageTemplate.setText(properties.message);

        var consumeOutMessageArg = new ConsumeOutMessageArg();
        consumeOutMessageArg.setAddress("+" + phone);
        consumeOutMessageArg.setOutMessageTemplate(outMessageTemplate);

        var sms = new ConsumeOutMessageRequest();
        sms.setAuth(auth);
        sms.getConsumeOutMessageArg().add(consumeOutMessageArg);

        var response = soapClient.sendSms(sms);

        if (response.getResponseCode() == ConsumeOutMessageResponseCode.OK) {
            log.info("Sms sent");
        } else if (response.getResponseCode() == ConsumeOutMessageResponseCode.ERROR_SYSTEM_BLOCKED) {
            var message = "Sms not sent, error: system blocked";
            log.error(message);
            throw new ServiceException(message);
        } else if (response.getResponseCode() == ConsumeOutMessageResponseCode.ERROR_MESSAGE_ID_DUPLICATE) {
            var message = "Sms not sent, error: messageId duplicate";
            log.error(message);
            throw new ServiceException(message);
        }
    }
}
