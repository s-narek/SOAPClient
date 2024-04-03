package com.example.client;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.ws.client.core.WebServiceTemplate;

import static com.example.client.Constants.PATH_XSD_SCHEME;

@Configuration
public class ClientConfig {
    @Bean
    public WebServiceTemplate webServiceTemplate() {
        WebServiceTemplate template = new WebServiceTemplate();

        Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
        marshaller.setContextPath(PATH_XSD_SCHEME);

        template.setMarshaller(marshaller);
        template.setUnmarshaller(marshaller);

        return template;
    }

}
