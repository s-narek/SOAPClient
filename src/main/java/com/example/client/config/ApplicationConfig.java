package com.example.client.config;

import com.example.client.config.properties.RootProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.ws.client.core.WebServiceTemplate;

@Configuration
@RequiredArgsConstructor
@EnableScheduling
public class ApplicationConfig {

    private final RootProperties rootProperties;

    @Bean
    public WebServiceTemplate smsWebServiceTemplate() {
        var marshallerProperties = rootProperties.getMarshaller();

        var marshaller = new Jaxb2Marshaller();
        marshaller.setContextPath(marshallerProperties.getContextPath());

        var template = new WebServiceTemplate();
        template.setMarshaller(marshaller);
        template.setUnmarshaller(marshaller);
        return template;
    }

}
