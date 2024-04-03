package com.example.client;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.ws.client.core.WebServiceTemplate;

@Configuration
public class ClientConfig {
    @Value("${sms-service-client.marshaller.context-path}")
    private String pathXsdScheme;

    @Bean
    public WebServiceTemplate smsWebServiceTemplate() {
        WebServiceTemplate template = new WebServiceTemplate();

        Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
        marshaller.setContextPath(pathXsdScheme);

        template.setMarshaller(marshaller);
        template.setUnmarshaller(marshaller);

        return template;
    }

}
